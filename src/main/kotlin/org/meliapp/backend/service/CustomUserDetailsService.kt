package org.meliapp.backend.service

import org.meliapp.backend.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository
            .findByEmail(username)
            .orElseThrow { UsernameNotFoundException(username) }

        return User
            .builder()
            .username(user.email)
            .password(user.password)
            .authorities(emptyList())
            .build()

    }
}