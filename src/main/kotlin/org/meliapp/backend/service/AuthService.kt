package org.meliapp.backend.service

import org.meliapp.backend.config.security.filters.jwt.JWTHelper
import org.meliapp.backend.dto.apc.auth.AuthRequestBody
import org.meliapp.backend.exception.apc.UserAlreadyRegisteredException
import org.meliapp.backend.model.RoleName
import org.meliapp.backend.model.User
import org.meliapp.backend.repository.RoleRepository
import org.meliapp.backend.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
) {
    fun register(registerRequest: AuthRequestBody) {
        checkIfUserExists(registerRequest.email)

        val user = User()
        user.email = registerRequest.email
        user.password = passwordEncoder.encode(registerRequest.password)

        val role = roleRepository
            .findRoleByName(RoleName.ROLE_ADMIN)
            .orElseThrow { RuntimeException("Role not found: ${RoleName.ROLE_USER.name}") }

        user.roles = setOf(role)

        userRepository.save(user)
    }

    fun login(loginRequest: AuthRequestBody): String {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication

        return userRepository
            .findByEmail(loginRequest.email)
            .map { user -> JWTHelper.generateToken(user.email) }
            .get()
    }

    private fun checkIfUserExists(email: String) {
        val user = userRepository.findByEmail(email)
        if (user.isPresent) throw UserAlreadyRegisteredException(email)
    }

}