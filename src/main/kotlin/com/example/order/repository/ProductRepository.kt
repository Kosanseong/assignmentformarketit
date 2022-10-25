package com.example.order.repository

import com.example.order.entity.product.Product
import com.example.order.entity.product.ProductId
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, ProductId> {
    fun findAllByProductIdIn(productId: List<ProductId>): List<Product>
}
