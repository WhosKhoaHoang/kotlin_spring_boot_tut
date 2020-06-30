package com.example.spring_boot_kotlin_tut

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


//@SpringBootTest is not declared here because it is used for integration tests, not unit tests?
@WebMvcTest(HotelController::class)
class HotelControllerTests {

	@MockBean
	private lateinit var hotelRepository: HotelRepository
	@Autowired
	private lateinit var mockMvc: MockMvc


	@Test
	fun `Empty hotel is empty`() {
		// Note that DbSeeder.kt doesn't seem to run for tests!
		//  - Proof?: "Database has been initialized does not appear on the console
		this.mockMvc.perform(MockMvcRequestBuilders.get("/hotels/all"))
				.andExpect(MockMvcResultMatchers.content().string("[]"))

		//this.mockMvc.get("/hotels/all")
	}


	@Test
	fun `Can find by name`() {
		val hotel = Hotel("APA Hotel", 3, 30)
		val hotelLst = mutableListOf<Hotel>()
		hotelLst.add(hotel)

		val testHotelName = "Test"
		`when`(this.hotelRepository.findByName(testHotelName)).thenReturn(hotelLst)

		// There's probably a more graceful way to specify the expected returned
		// content. Perhaps you can override Hotel's toString method?
		val expectedContent = "[{\"name\":\"APA Hotel\",\"classification\":3," +
							  "\"numRooms\":30,\"id\":0,\"numFreeRooms\":30}]"
		this.mockMvc.perform(MockMvcRequestBuilders.get("/hotels/"+testHotelName))
				.andExpect(MockMvcResultMatchers.content().string(expectedContent))
	}


	@Test
	fun `Can post`() {
		// . For now, just check if we received an OK response after POST'ing

		val hotel = Hotel("APA Hotel", 3, 30)
		val hotelLst = mutableListOf<Hotel>()
		hotelLst.add(hotel)

		`when`(this.hotelRepository.findByName("APA Hotel")).thenReturn(hotelLst)
		//`when`(this.hotelRepository.save(Mockito.any<Hotel>())).thenAnswer { i -> i.getArguments()[0] }
		// NOTE: If writing unit test, don't expect to be able to save to repository? Need
		//       to make an integration test for that? See here:
		// 			https://stackoverflow.com/questions/47738937/

		val payload = jacksonObjectMapper().writeValueAsString(
				mapOf("hotelName" to "APA Hotel", "numGuests" to 2))

		// ===== Old way of using mockMvc =====
		//this.mockMvc.perform(MockMvcRequestBuilders.post("/hotels/checkin")
		//		.content(payload))
		// ===== New way of using mockMvc =====
		this.mockMvc.post("/hotels/checkin") {
			contentType = MediaType.APPLICATION_JSON
			content = payload
		}.andExpect { status { isOk } }

		// Make a GET request to "/all" endpoint to check if you actually created something???
		//this.mockMvc.perform(MockMvcRequestBuilders.get("/hotels/all"))
		//		.andExpect(MockMvcResultMatchers.content().string("[]"))
	}
}
