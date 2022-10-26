package com.example.order.entity.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
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
