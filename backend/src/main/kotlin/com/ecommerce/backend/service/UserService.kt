package com.ecommerce.backend.service

import com.ecommerce.backend.dto.UserDTO
import com.ecommerce.backend.mapper.UserFormMapper
import com.ecommerce.backend.mapper.UserViewMapper
import com.ecommerce.backend.repository.UserRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class UserService (
    private val repository: UserRepository,
    @Lazy private val mapper: UserFormMapper,
    @Lazy private val viewMapper: UserViewMapper
) {
    fun createUser(userDTO: UserDTO): UserDTO {
        val user = mapper.map(userDTO)
        repository.save(user)
        return viewMapper.map(user)
    }

}
