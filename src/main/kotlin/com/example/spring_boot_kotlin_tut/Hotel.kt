package com.example.spring_boot_kotlin_tut

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


// Note that an "Entity" represents a table stored in a database
@Entity
class Hotel(val name: String, val classification: Int, val numRooms: Int) {
    // When we declare a variable with the "val" keyword, then that
    // indicates an immutable variable (works like the "final" keyword
    // in Java). "var" is the opposite of "val".

    // Like in python, you don't explicitly need to define
    // getters and setters for these instance variables
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    var id: Long = 0
    var numFreeRooms: Int = numRooms

    // You can define additional constructors for a
    // Kotlin class with this constructor keyword.
    // Here, we're implementing a constructor with
    // default argument values (though it seems like
    // the application actually runs just fine without it...)
    constructor(): this("", 0, 0)

    fun checkInGuests(numGuests: Int) {
        if (this.numFreeRooms >= numGuests) {
            this.numFreeRooms -= numGuests
        }
    }

    fun checkOut(numGuests: Int) {
        this.numFreeRooms += numGuests
    }

}