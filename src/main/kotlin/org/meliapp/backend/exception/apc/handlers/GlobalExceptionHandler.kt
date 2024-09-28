package org.meliapp.backend.exception.apc.handlers
import io.jsonwebtoken.JwtException
import org.meliapp.backend.exception.apc.UserAlreadyRegisteredException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException::class, JwtException::class)
    fun handleAuthException(e: RuntimeException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UserAlreadyRegisteredException::class)
    fun handleUserAlreadyRegisteredException(e: UserAlreadyRegisteredException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

}