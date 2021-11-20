package com.kotlinexample.businesscontentapi


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/businesscontentapi")
class BusinessController(private val businessRepository: BusinessRepository) {

    @GetMapping("")
    fun getAll(@RequestParam(value = "businessname", required = false, defaultValue = "") businessNameFilter: String,
               @RequestParam(value = "businessphone", required = false, defaultValue = "") businessPhoneFilter: String,
               @RequestParam(value = "minrating", required = false, defaultValue = "") minRating: String,
               @RequestParam(value = "maxrating", required = false, defaultValue = "") maxRating: String,
               @RequestParam(value = "price", required = false, defaultValue = "") priceFilter: String): ResponseEntity<List<Business>> {
        val MAX_RATE = 10
        val minRatingFilter = if (!minRating.isNullOrBlank()) minRating.toInt() else 0
        val maxRatingFilter = if (!maxRating.isNullOrBlank()) maxRating.toInt() else MAX_RATE
        return ResponseEntity(businessRepository.findAll()
                .filter { it.name.contains(businessNameFilter, true) }
                .filter { it.phone.contains(businessPhoneFilter, true) }
                .filter { it.rating >= minRatingFilter }
                .filter { it.rating <= maxRatingFilter }
                .filter { it.price.contains(priceFilter, true) },

                HttpStatus.OK
        )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Long> = ResponseEntity(businessRepository.count(),
            HttpStatus.OK)


    @GetMapping("/{id}")
    fun getBusiness(@PathVariable id: Long): ResponseEntity<Optional<Business>> {
        if (businessRepository.existsById(id)) {
            return ResponseEntity(businessRepository.findById(id), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping()
    fun createBusiness(@RequestBody business: Business): ResponseEntity<Business> {
        return ResponseEntity(businessRepository.save(business), HttpStatus.CREATED)
    }


    @PutMapping("/{id}")
    fun updateBusiness(@PathVariable id: Long, @RequestBody businessChanges: Business): ResponseEntity<Business?> {
        if (businessRepository.existsById(id)) {
            val originalBusiness = businessRepository.findById(id).get()
            val updatedBusiness = Business(
                    id = id,
                    name = if (businessChanges.name != "") businessChanges.name else originalBusiness.name,
                    phone = if (businessChanges.phone != "") businessChanges.phone else originalBusiness.phone,
                    price = if (businessChanges.price != "") businessChanges.price else originalBusiness.price,
                    rating = if (businessChanges.rating != 0) businessChanges.rating else originalBusiness.rating
            )
            return ResponseEntity(businessRepository.save(updatedBusiness), HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteBusiness(@PathVariable id: Long): ResponseEntity<Business?> {
        if (businessRepository.existsById(id)) {
            businessRepository.deleteById(id)
            return ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}