package com.realtimeticketingsystem.springserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringServerApplicationTests {

	@Autowired
	private  VendorRepository vendorRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void addVendor(){
		Vendor vendor = new Vendor();
		vendor.setName("Thanuri");
		vendor.setAge(23);
		vendorRepository.save(vendor);
	}

}
