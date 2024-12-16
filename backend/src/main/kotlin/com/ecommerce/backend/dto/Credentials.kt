package com.ecommerce.backend.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class Credentials (
    @field:JsonProperty("username") @field:NotBlank
    val username:String="",
    @field:JsonProperty("password") @field:NotBlank
    val password:String =""
) {
}