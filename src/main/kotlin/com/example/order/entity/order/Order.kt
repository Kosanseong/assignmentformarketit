package com.example.order.entity.order

import com.example.order.entity.product.Product
import com.example.order.entity.user.User
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue
    val orderId: OrderId = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    val productQuantity: Int,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    var lastModifiedAt: OffsetDateTime? = null,
    @OneToOne
    @JoinColumn(name = "product_id")
    val product: Product,
    @Enumerated(EnumType.STRING)
    var orderState: OrderState = OrderState.ACCEPTED
) {
    enum class OrderState {
        ACCEPTED, COMPLETED
    }

    fun complete() {
        this.orderState = OrderState.COMPLETED
        this.lastModifiedAt = OffsetDateTime.now()
    }

    val totalPrice = product.price * productQuantity
}




typealias OrderId = Long
