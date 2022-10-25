package com.example.order.controller

import com.example.order.entity.product.Product
import com.example.order.repository.ProductRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productRepository: ProductRepository,
) {
    @PostMapping("/products")
    fun postProduct() {
        val product = Product(
            productName = "상품1",
            price = 1000
        )

        val product2 = Product(
            productName = "상품2",
            price = 2000
        )

        val product3 = Product(
            productName = "상품3",
            price = 3000
        )

        val productList = listOf(product, product2, product3)

        productRepository.saveAll(productList)
    }
}
