package com.realtimeticketingsystem.springserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorDoa {
    //data access object

    @Autowired
    private VendorRepository vendorRepository;

    public void save(Vendor vendor){
        vendorRepository.save(vendor);
    }


}
