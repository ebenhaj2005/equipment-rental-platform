package com.rentalplatform.equipmentrental;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class EquipmentRentalApplicationTests {

	@Test
	void contextLoads() {
		// Test that Spring context loads successfully
	}
}