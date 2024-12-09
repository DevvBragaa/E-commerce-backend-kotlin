package com.ecommerce.service

import com.ecommerce.domain.UserDetail
import com.ecommerce.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserAuthenticationService (
    private val userRepository: UserRepository
) : UserDetailsService{


    override fun loadUserByUsername(email: String?): UserDetails {
        val user = email?.let { userRepository.findByUsername(it) } ?:throw RuntimeException()
        return UserDetail(user)
    }
}
