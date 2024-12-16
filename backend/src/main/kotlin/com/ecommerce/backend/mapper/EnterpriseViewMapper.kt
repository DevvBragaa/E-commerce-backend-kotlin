package com.ecommerce.backend.mapper

import com.ecommerce.backend.domain.Enterprise
import com.ecommerce.backend.dto.EnterpriseDTO
import org.springframework.stereotype.Component

@Component
class EnterpriseViewMapper(
    private val userViewMapper: UserViewMapper,
) : Mapper<Enterprise, EnterpriseDTO> {
    override fun map(t: Enterprise): EnterpriseDTO {
        return EnterpriseDTO(
            id = t.id,
            name = t.name,
            email = t.email,
            users = t.users.map { userViewMapper.map(it) }
        )
    }

    override fun mapList(t: List<Enterprise>): List<EnterpriseDTO> {
        return t.map { map(it) }
    }
}
