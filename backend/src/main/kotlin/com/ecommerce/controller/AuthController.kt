package com.ecommerce.controller

import com.ecommerce.dto.Credentials
import com.ecommerce.security.JWTUtil
import com.ecommerce.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/login")
class AuthController (
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) {


    @PostMapping
    fun loginRequest(@RequestBody @Valid credentials: Credentials): ResponseEntity<Any> {
        try {
            // Criação do objeto de autenticação com as credenciais fornecidas
            val usernamePasswordAuthToken = UsernamePasswordAuthenticationToken(
                credentials.username, credentials.password
            )
            // Autenticação via AuthenticationManager
            val auth: Authentication = authenticationManager.authenticate(usernamePasswordAuthToken)

            // Se a autenticação for bem-sucedida, o token JWT é gerado
            SecurityContextHolder.getContext().authentication = auth
            val user = auth.principal as org.springframework.security.core.userdetails.User
            val token = jwtUtil.generateToken(user.username, user.authorities)

            // Retorna o token JWT no corpo da resposta
            return ResponseEntity.ok("Bearer $token")
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("Login failed: ${e.message}")
        }
    }

    @PostMapping("/register")
    fun create(
        @RequestBody @Valid userDTO: UserDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<String> {
        val dto = userService.createUser(userDTO)
        val uri = uriComponentsBuilder.path("/users").buildAndExpand(dto.id).toUri()
        return ResponseEntity.created(uri).body("Registration completed successfully!!")
    }

}