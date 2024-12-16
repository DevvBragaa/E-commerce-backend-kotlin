package com.ecommerce.backend.repository

import com.ecommerce.backend.domain.ExchangeRate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExchangeRateRepository : JpaRepository<ExchangeRate, Long> {
}