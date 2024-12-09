package com.ecommerce.config

import com.ecommerce.service.UserAuthenticationService
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class ConfigurationBeans (
    private val userAuthenticationService: UserAuthenticationService
){


    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userAuthenticationService)?.passwordEncoder(bCryptPasswordEncoder())
    }
}