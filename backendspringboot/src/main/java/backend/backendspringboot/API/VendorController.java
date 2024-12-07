package backend.backendspringboot.API;

import backend.backendspringboot.application.VendorService;
import backend.backendspringboot.domian.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {
    private Vendor vendor;
    @Autowired
    public VendorController(Vendor vendor) {
        this.vendor = vendor;
    }


    private VendorService vendorService;
    @Autowired
    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @PostMapping("/add")
    public String addTickets(@RequestParam String vendorName) {
        vendor.setVendorName(vendorName);
        new Thread(() -> vendorService.run()).start();
        return "vendor " + vendorName +" added tickets.";
    }

    @GetMapping("/isThreadRunning")
    public boolean isThreadRunning() {
        return vendorService.isThreadRunning();
    }
}
