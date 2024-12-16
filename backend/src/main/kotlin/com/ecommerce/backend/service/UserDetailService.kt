package com.ecommerce.backend.service


import com.ecommerce.backend.domain.UserDetail
import com.ecommerce.backend.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailService (
    private val userRepository: UserRepository
) : UserDetailsService{


    override fun loadUserByUsername(email: String?): UserDetails {
        val user = email?.let { userRepository.findByEmail(it) }
            ?: throw UsernameNotFoundException("User not found with email: $email")
        return UserDetail(user)
    }
}

