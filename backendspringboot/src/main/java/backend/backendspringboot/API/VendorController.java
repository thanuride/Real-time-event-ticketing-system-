package backend.backendspringboot.API;

import backend.backendspringboot.application.TicketPoolService;
import backend.backendspringboot.application.VendorService;
import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.TicketPool;
import backend.backendspringboot.domian.Vendor;
import backend.backendspringboot.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {
    private final ConcurrentHashMap<String, Thread> vendorThreads = new ConcurrentHashMap<>();
    // To store the service for each vendor
    private final ConcurrentHashMap<String, VendorService> vendorServices = new ConcurrentHashMap<>();

    @Autowired
    private Vendor vendor;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private TicketConfiguration ticketConfiguration;
    @Autowired
    private TicketPool ticketPool;
    @Autowired
    private TicketPoolService ticketPoolService;


    @PostMapping("/start")
    public String startVendorThread(@RequestParam String vendorName) {
        // Check if the vendor's thread is already running
        if (vendorThreads.containsKey(vendorName)) {
            return "Vendor thread is already running for: " + vendorName;
        }

        // Create and set the vendor dynamically
        Vendor vendor = new Vendor(vendorName);
        VendorService newVendorService = new VendorService(ticketConfiguration,vendor,ticketPool,ticketPoolService);
        vendorServices.put(vendorName, newVendorService); // Store the vendor service for future operations


        // Create and start the thread
        Thread vendorThread = new Thread(newVendorService);
        vendorThreads.put(vendorName, vendorThread);
        vendorThread.start();

        Log.logInfo("Started thread for vendor: " + vendorName);
        return "Vendor thread started for: " + vendorName;
    }

    @PostMapping("/stop")
    public String stopVendorThread(@RequestParam String vendorName) {
        // Check if a thread exists for the vendor and is alive
        Thread vendorThread = vendorThreads.get(vendorName);
//        if (vendorThread == null || !vendorThread.isAlive()) {
//            return "No thread found or the thread has already finished for vendor: " + vendorName;
//        }

        // Request the thread to stop and interrupt it
        vendorServices.get(vendorName).stopRequest();
//        vendorThread.interrupt();
        vendorThreads.remove(vendorName);

        Log.logInfo("Stopped thread for vendor " + vendorName);
        return "Vendor thread stopped for " + vendorName;
    }


    @PostMapping("/saveVendor")
    public String save(@RequestParam String name) {
        Vendor vendor = new Vendor();
        vendor.setVendorName(name);
        vendorService.save(vendor);
        return "Vendor " + name + " added successfully.";
    }


}



