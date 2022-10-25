package com.example.order.service

import com.example.order.entity.order.Order
import com.example.order.entity.order.OrderId
import com.example.order.entity.product.Product
import com.example.order.entity.user.UserId
import com.example.order.exception.BusinessException
import com.example.order.exception.ErrorCode
import com.example.order.repository.OrderRepository
import com.example.order.repository.ProductRepository
import com.example.order.repository.UserRepository
import com.example.order.service.dto.OrderDto
import com.example.order.service.dto.toDto
import com.example.order.service.model.OrderModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
) {
    fun acceptOrder(
        userId: UserId,
        modelList: List<OrderModel>,
    ): List<Order> {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw BusinessException(ErrorCode.UserNotFoundException)

        val productList = productRepository
            .findAllByProductIdIn(modelList.map { it.productId })

        isNotProductEmptyOrThrow(productList)

        val orderList = modelList.mapNotNull { model ->
            productList.find { productList ->
                productList.productId == model.productId
            }?.let { product ->
                model.toEntity(
                    user = user,
                    product = product
                )
            }
        }

        return orderRepository.saveAll(orderList)
    }

    fun completeOrder(orderId: OrderId): Order {
        val order = getOrderEntity(orderId)

        order.complete()

        return order
    }

    fun getOrder(orderId: OrderId): OrderDto {
        val order = getOrderEntity(orderId)

        return order.toDto()
    }

    fun getOrderPage(userId: UserId, pageable: Pageable): Page<OrderDto> {
        val orderList = orderRepository.findAllByUser_UserIdOrderByCreatedAt(userId, pageable)

        return orderList.map { it.toDto() }
    }

    private fun getOrderEntity(orderId: OrderId): Order {
        return orderRepository.findByIdOrNull(orderId)
            ?: throw BusinessException(ErrorCode.OrderNotFoundException)
    }

    private fun isNotProductEmptyOrThrow(productList: List<Product>) {
        if(productList.isEmpty()) {
            throw BusinessException(ErrorCode.ProductNotFoundException)
        }
    }
}
