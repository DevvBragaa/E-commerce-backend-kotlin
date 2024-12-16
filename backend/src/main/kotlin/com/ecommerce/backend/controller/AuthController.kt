package com.ecommerce.backend.controller

import com.ecommerce.backend.dto.UserDTO
import com.ecommerce.backend.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/login")
class AuthController (
    private val userService: UserService,
) {

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