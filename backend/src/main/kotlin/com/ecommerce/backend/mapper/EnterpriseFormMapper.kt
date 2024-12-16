package com.ecommerce.backend.mapper

import com.ecommerce.backend.domain.Enterprise
import com.ecommerce.backend.dto.EnterpriseDTO
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class EnterpriseFormMapper(
    @Lazy private val userMapper: UserFormMapper
) : Mapper<EnterpriseDTO, Enterprise> {
    override fun map(t: EnterpriseDTO): Enterprise {
        return Enterprise(
            id = t.id,
            name = t.name,
            email = t.email,
            users = t.users!!.map { userMapper.map(it) }
        )
    }

    override fun mapList(t: List<EnterpriseDTO>): List<Enterprise> {
        return t.map { map(it) }
    }
}
