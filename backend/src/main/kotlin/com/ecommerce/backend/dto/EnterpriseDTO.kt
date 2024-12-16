package com.ecommerce.backend.dto

data class EnterpriseDTO (
    val id: Long?=null,
    val name: String="",
    val email: String="",
    val users:List<UserDTO>?= emptyList(),
){

}
