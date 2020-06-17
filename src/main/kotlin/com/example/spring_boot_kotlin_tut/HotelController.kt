package com.example.spring_boot_kotlin_tut

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hotels")
class HotelController(val hotelRepository: HotelRepository) {

    @GetMapping("/all")
    // Example of how to declare and define a method in a single line:
    fun all(): MutableIterable<Hotel> = this.hotelRepository.findAll()

    @GetMapping("/(name)")
    fun byName(@PathVariable(value = "name") name: String) : List<Hotel> {
        val hotelsByName = this.hotelRepository.findByName(name)
        return hotelsByName
    }

    @PostMapping("/checkin")
    // The CheckInRequest type will be defined as a Java class. This is an
    // example of how we can integrate Java code with Kotlin.
    fun checkIn(@RequestBody checkInRequest: CheckInRequest) {
        // NOTE: In production, you'd have findByName() return an Optional in
        //       case no results exists for a checkInRequest.hotelName. Also,
        //       I think get() will cause an error if findByName() doesn't return
        //       an object that supports get() or if the object is some container
        //       type that is empty (so no 0th element exists)
        val hotel = this.hotelRepository.findByName(checkInRequest.hotelName).get(0)
        hotel.checkInGuests(checkInRequest.numGuests)
        this.hotelRepository.save(hotel)
    }
}