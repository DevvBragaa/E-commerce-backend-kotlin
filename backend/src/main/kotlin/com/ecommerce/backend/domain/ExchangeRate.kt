package com.ecommerce.backend.domain

import jakarta.persistence.*


@Entity
@Table(name = "TB_EXCHANGE_RATE")
class ExchangeRate (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(nullable = false)
    var currency: String,

    @Column(nullable = false)
    var rate: Double

) {
}