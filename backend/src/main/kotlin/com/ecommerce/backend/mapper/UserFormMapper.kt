package com.ecommerce.backend.mapper

import com.ecommerce.backend.domain.User
import com.ecommerce.backend.dto.UserDTO
import org.springframework.stereotype.Component

@Component
class UserFormMapper (
    private val enterpriseFormMapper: EnterpriseFormMapper
) : Mapper<UserDTO, User> {
    override fun map(t: UserDTO): User {
        return User(
            id = t.id,
            username = t.username,
            email = t.email,
            password = t.password,
            enterprise = t.enterprise?.let { enterpriseFormMapper.map(it) },
            authority = t.authority!!
        )
    }

    override fun mapList(t: List<UserDTO>): List<User> {
        return t.map { map(it) }
    }
}