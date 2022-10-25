package com.example.order.repository

import com.example.order.entity.order.Order
import com.example.order.entity.order.OrderId
import com.example.order.entity.user.UserId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, OrderId> {
    fun findAllByUser_UserIdOrderByCreatedAt(userId: UserId, pageable: Pageable): Page<Order>
}
