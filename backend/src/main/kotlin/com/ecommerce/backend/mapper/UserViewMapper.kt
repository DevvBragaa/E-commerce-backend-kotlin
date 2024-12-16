package com.ecommerce.backend.mapper

import com.ecommerce.backend.domain.User
import com.ecommerce.backend.dto.UserDTO
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class UserViewMapper (
    @Lazy private val enterpriseViewMapper: EnterpriseViewMapper
): com.ecommerce.backend.mapper.Mapper<User, UserDTO> {
    override fun map(t: com.ecommerce.backend.domain.User): UserDTO {
        return UserDTO(
            id = t.id,
            username = t.username,
            email = t.email,
            enterprise = t.enterprise?.let { enterpriseViewMapper.map(it) }
        )
    }

    override fun mapList(t: List<com.ecommerce.backend.domain.User>): List<UserDTO> {
        return t.map{map(it)}
    }
}