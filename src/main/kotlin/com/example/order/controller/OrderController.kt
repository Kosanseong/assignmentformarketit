package com.example.order.controller

import com.example.order.controller.request.OrderRequest
import com.example.order.controller.response.OrderPageResponse
import com.example.order.controller.response.OrderResponse
import com.example.order.entity.order.OrderId
import com.example.order.entity.user.UserId
import com.example.order.service.OrderService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("/{id}/accept")
    fun acceptOrder(
        @PathVariable id: UserId,
        @RequestBody request: OrderRequest,
    ): List<OrderId> {
        val orderList = orderService.acceptOrder(userId = id, modelList = request.toModel(id))

        return orderList.map { it.orderId }
    }

    @PatchMapping("/{id}/complete")
    fun completeOrder(
        @PathVariable id: OrderId,
    ): OrderId {
        return orderService.completeOrder(id).orderId
    }

    @GetMapping("/{id}")
    fun getOrder(
        @PathVariable id: Long,
    ): OrderResponse {
        return orderService.getOrder(id).toResponse()
    }

    @GetMapping
    fun getOrders(
        @RequestParam page: Int?,
        @RequestParam size: Int?,
        @RequestParam userId: UserId,
    ): OrderPageResponse {
        val pageable = PageRequest.of(page ?: 0, size ?: 10)
        val orderPage = orderService.getOrderPage(userId, pageable)

        return OrderPageResponse(orderPage.map { it.toResponse() })
    }
}
