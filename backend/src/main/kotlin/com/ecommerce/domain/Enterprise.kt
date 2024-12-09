package com.ecommerce.domain

import jakarta.persistence.*


@Entity
@Table(name = "TB_ENTERPRISE")
class Enterprise(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @OneToMany(mappedBy = "enterprise", cascade = [CascadeType.ALL])
    var users: List<User> = mutableListOf()

) {


}
