package org.meliapp.backend.controller

import io.swagger.v3.oas.annotations.Operation
import org.meliapp.backend.dto.apc.auth.AuthRequestBody
import org.meliapp.backend.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
)
{
    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    fun register(@RequestBody authRequestBody: AuthRequestBody): ResponseEntity<String> {
        authService.register(authRequestBody)
        return ResponseEntity.ok("Success")
    }

    @Operation(summary = "Log in")
    @PostMapping("/login")
    fun login(@RequestBody authRequestBody: AuthRequestBody): ResponseEntity<String> {
        return ResponseEntity.ok(authService.login(authRequestBody))
    }

}