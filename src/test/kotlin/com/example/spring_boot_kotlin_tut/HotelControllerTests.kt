package com.example.spring_boot_kotlin_tut

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
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
		mockMvc.perform(MockMvcRequestBuilders.get("/hotels/all"))
				.andExpect(MockMvcResultMatchers.content().string("[]"))
	}
}
