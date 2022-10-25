package com.example.order.entity.user

import com.example.order.entity.order.Order
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue
    val userId: UserId = 0,
    val username: String,
)


typealias UserId = Long
