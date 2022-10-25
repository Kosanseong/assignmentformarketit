package com.example.order.repository

import com.example.order.entity.user.User
import com.example.order.entity.user.UserId
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, UserId>
