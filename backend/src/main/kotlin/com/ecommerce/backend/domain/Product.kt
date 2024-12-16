package com.ecommerce.backend.domain

import jakarta.persistence.*

@Entity
@Table(name = "TB_PRODUCT")
class Product (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false)
    var stock: Int, // Quantidade disponível no estoque

    @ManyToOne
    @JoinColumn(name = "ENTERPRISE_ID")
    var enterprise: Enterprise? = null
) {
}