package com.example.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private  Vendor vendor;

    public VendorController(Vendor vendor) {
        this.vendor = vendor;
    }

    @PostMapping("/add")
    public String addTickets(@RequestParam String name, @RequestParam String id) {

        // Set vendor details
        vendor.setVendorName(name);
        vendor.setVendorID(id);

        // Run the vendor logic in a thread
//        Thread vendorThread = new Thread(vendor);
//        vendorThread.start();

        return "Vendor " + name + " is adding tickets.";
    }
}
