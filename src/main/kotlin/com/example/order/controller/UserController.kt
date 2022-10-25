package com.example.order.controller

import com.example.order.entity.user.User
import com.example.order.repository.UserRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userRepository: UserRepository,
) {
    @PostMapping("/users")
    fun postUser() {
        val user = User(
            userId = 0,
            username = "고산성",
        )

        userRepository.save(user)
    }
}
