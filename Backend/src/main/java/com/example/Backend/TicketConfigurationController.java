package com.example.Backend;

import org.hibernate.type.internal.CustomMutabilityConvertedPrimitiveBasicTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/configuration")
public class TicketConfigurationController {

    private TicketConfiguration ticketConfiguration;

    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }

    private Vendor vendor;

    @Autowired
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @PostMapping("/setConfigurations")
    public String configure(@RequestParam int totalTickets , @RequestParam int ticketReleaseRate,
                          @RequestParam int retrievalRate, @RequestParam int maxCapacity ){

        vendor.setTicketCount(totalTickets);
        ticketConfiguration.setTicketReleaseRate(ticketReleaseRate);
        ticketConfiguration.setRetrievalRate(retrievalRate);
        ticketConfiguration.setTicketCapacity(maxCapacity);

        // Run the vendor logic in a thread
        Thread vendorThread = new Thread(vendor);
        vendorThread.start();

        return "configured the parameters";
    }
}
