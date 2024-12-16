package com.ecommerce.backend.config

import com.ecommerce.backend.security.JWTAuthenticationFilter
import com.ecommerce.backend.security.JWTLoginFilter
import com.ecommerce.backend.security.JWTUtil
import com.ecommerce.backend.service.UserDetailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val configuration: AuthenticationConfiguration,
    private val jwtUtil: JWTUtil,
    private val userDetailService: UserDetailService
) {
    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(): AuthenticationManager {
        val authBuilder = DaoAuthenticationProvider()
        authBuilder.setUserDetailsService(userDetailService)
        authBuilder.setPasswordEncoder(bCryptPasswordEncoder())
        return ProviderManager(authBuilder)
    }


    private val PUBLIC_URLS: Array<String> = arrayOf("/login", "/login/register")

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val const: String = "/users"
        http.csrf { it.disable() }
            .headers { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(HttpMethod.POST, *PUBLIC_URLS ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                JWTLoginFilter(authenticationManager = configuration.authenticationManager, jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter().javaClass
            )
            .addFilterBefore(JWTAuthenticationFilter(jwtUtil =  jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
        return http.build()
    }
}
