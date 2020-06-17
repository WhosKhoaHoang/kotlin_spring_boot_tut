package com.example.spring_boot_kotlin_tut

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

// . This DBSeeder will populate our database with data
// . It seems that without the @Component annotation, the application
//   will not create an instance of this class and call run() on it
//   upon execution.
@Component
class DbSeeder(val hotelRepository: HotelRepository) : CommandLineRunner {

    // vararg args: String? indicates a variable number
    // or arguments that may or may not be passed to
    // this run method.
    override fun run(vararg args: String?) {
        this.hotelRepository.deleteAll()

        // . Recall that val indicates an immutablility. It's similar to "final"
        //   in Java or "const" in JavaScript.
        // . Notice that we don't need to use the "new" keyword to construct
        //   new objects in Kotlin.
        val apaHotel = Hotel("APA Hotel", 3, 30)
        val parkHyatt = Hotel("Park Hyatt", 5, 100)
        // Construct object with named args:
        val myStays = Hotel(name="MyStays", classification = 4, numRooms = 40)

        val hotels = mutableListOf<Hotel>()
        hotels.add(apaHotel)
        hotels.add(parkHyatt)
        hotels.add(myStays)

        // Need to use saveAll() for lists and save() for single objects
        this.hotelRepository.saveAll(hotels)
        println("\n=== Database has been initialized ===\n")
    }
}