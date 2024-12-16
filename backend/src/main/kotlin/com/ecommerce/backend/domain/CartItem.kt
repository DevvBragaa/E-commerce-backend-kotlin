package com.ecommerce.backend.domain

import jakarta.persistence.*


@Entity
@Table(name = "TB_CART_ITEM")
class CartItem (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    var product: Product,

    @Column(nullable = false)
    var quantity: Int,

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    var cart: Cart? = null
) {

}
