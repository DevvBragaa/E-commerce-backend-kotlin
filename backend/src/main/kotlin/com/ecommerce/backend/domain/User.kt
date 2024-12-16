package com.ecommerce.backend.domain

import com.ecommerce.backend.util.Role
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority


@Entity
@Table(name = "TB_USER")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(nullable = false)
    var username: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @ManyToOne
    @JoinColumn(name = "ENTERPRISE_ID")
    var enterprise: com.ecommerce.backend.domain.Enterprise? = null,

    val authority: Role
    ) : GrantedAuthority{
    override fun getAuthority(): String? {
        return this.authority.toString()
    }
}
