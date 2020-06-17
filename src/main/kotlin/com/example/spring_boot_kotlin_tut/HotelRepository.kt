package com.example.spring_boot_kotlin_tut

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

// A repository is a mechanism for encapsulating storage,
// retrieval, and search behavior which emulates a collection of objects.
@Repository
interface HotelRepository : CrudRepository<Hotel, Long> {
    // The colon symbol ":" is used to indicate extension of another class
    fun findByName(name: String): List<Hotel>
}