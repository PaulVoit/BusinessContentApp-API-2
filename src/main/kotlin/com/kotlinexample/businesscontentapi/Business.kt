package com.kotlinexample.businesscontentapi

import javax.persistence.*

@Entity
data class Business(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        var name: String,
        var phone: String,
        var rating: Int,
        var price: String
)