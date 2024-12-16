package com.ecommerce.backend.dto

import com.ecommerce.backend.util.Role

data class UserDTO (
    val id:Long ?=null,
    val username: String="",
    val email: String="",
    val password: String="",
    val enterprise:EnterpriseDTO? = null,
    val authority: Role? = null
) {
}