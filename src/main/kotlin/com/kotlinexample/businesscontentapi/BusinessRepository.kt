package com.kotlinexample.businesscontentapi

import org.springframework.data.repository.CrudRepository

interface BusinessRepository: CrudRepository<Business, Long> {
}