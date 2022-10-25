package com.example.order.service

import com.example.order.entity.order.Order
import com.example.order.entity.product.Product
import com.example.order.entity.user.User
import com.example.order.exception.BusinessException
import com.example.order.exception.ErrorCode
import com.example.order.repository.OrderRepository
import com.example.order.repository.ProductRepository
import com.example.order.repository.UserRepository
import com.example.order.service.dto.OrderDto
import com.example.order.service.model.OrderModel
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull

@ExtendWith(MockKExtension::class)
class OrderServiceTest {
    @MockK
    private lateinit var orderRepository: OrderRepository
    @MockK
    private lateinit var userRepository: UserRepository
    @MockK
    private lateinit var productRepository: ProductRepository
    @InjectMockKs
    private lateinit var targetService: OrderService

    @Test
    fun `단일 주문 조회시 주문 아이디가 없으면 에러를 발생시켜야한다`() {
        //given
        val orderId = 1L
        every { orderRepository.findByIdOrNull(orderId) } returns null

        //when, then
        Assertions.assertThatThrownBy { targetService.getOrder(orderId) }
            .isInstanceOf(BusinessException::class.java).hasMessageContaining(ErrorCode.OrderNotFoundException.message)
    }

    @Test
    fun `주문 목록이 없으면 주문 목록 페이지 내 컨텐트는 빈 리스트이다`() {
        //given
        val userId = 1L
        val page = 0
        val size = 5
        val pageable = PageRequest.of(page, size)
        every { orderRepository.findAllByUser_UserIdOrderByCreatedAt(userId, pageable) } returns Page.empty()

        //when
        val result = targetService.getOrderPage(userId, pageable)


        //then
        Assertions.assertThat(result.content).isEqualTo(emptyList<OrderDto>())
    }

    @Test
    fun `주문 접수 시 유저 아이디가 알맞지 않으면 에러를 발생시킨다`() {
        val userId = 1L
        val model = emptyList<OrderModel>()

        every { userRepository.findByIdOrNull(userId) } returns null

        Assertions.assertThatThrownBy { targetService.acceptOrder(userId, model) }
            .isInstanceOf(BusinessException::class.java).hasMessageContaining(ErrorCode.UserNotFoundException.message)
    }

    @Test
    fun `요청 들어온 상품이 없는 경우 에러를 발생시킨다`() {
        //given
        val userId = 1L
        val user = User(
            userId = userId,
            username = "고산성",
        )
        val orderModel = OrderModel(
            productQuantity = 1,
            productId = 1L
        )
        every { userRepository.findByIdOrNull(userId) } returns user
        every { productRepository.findAllByProductIdIn(listOf(orderModel.productId)) } returns emptyList()

        //when
        //then
        Assertions.assertThatThrownBy { targetService.acceptOrder(userId, listOf(orderModel)) }
            .isInstanceOf(BusinessException::class.java)
            .hasMessageContaining(ErrorCode.ProductNotFoundException.message)
    }

    @Test
    fun `주문 완료 시 주문 상태가 완료로 변해야한다`() {
        //given
        val orderId = 1L
        val userId = 1L
        val user = User(
            userId = userId,
            username = "고산성",
        )
        val product = Product(
            productId = 1L,
            productName = "예시 상품",
            price = 1000
        )
        val order = Order(
            orderId = orderId,
            user = user,
            productQuantity = 3,
            lastModifiedAt = OffsetDateTime.now(),
            product = product,
            orderState = Order.OrderState.ACCEPTED
        )

        every { orderRepository.findByIdOrNull(orderId) } returns order

        //when
        val result = targetService.completeOrder(orderId)

        //then
        Assertions.assertThat(result.orderState).isEqualTo(Order.OrderState.COMPLETED)
    }
}
