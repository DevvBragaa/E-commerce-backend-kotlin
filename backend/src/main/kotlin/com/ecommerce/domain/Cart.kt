package com.ecommerce.domain

import jakarta.persistence.*


@Entity
@Table(name = "TB_CART")
class Cart(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL])
    var items: List<CartItem> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    var user: User? = null
) {

}