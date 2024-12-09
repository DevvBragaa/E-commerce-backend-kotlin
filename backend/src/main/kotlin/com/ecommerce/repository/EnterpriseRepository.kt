package com.ecommerce.repository

import com.ecommerce.domain.Enterprise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnterpriseRepository : JpaRepository<Enterprise, Long> {
}