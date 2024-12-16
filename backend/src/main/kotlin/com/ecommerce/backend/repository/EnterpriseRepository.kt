package com.ecommerce.backend.repository

import com.ecommerce.backend.domain.Enterprise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnterpriseRepository : JpaRepository<Enterprise, Long> {
}