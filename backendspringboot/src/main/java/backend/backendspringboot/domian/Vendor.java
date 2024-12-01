package backend.backendspringboot.domian;


import org.springframework.stereotype.Component;

@Component
public class Vendor{

    private String vendorName;
    private String vendorId;

    public Vendor(String vendorName, String vendorId) {
        this.vendorName = vendorName;
        this.vendorId = vendorId;
    }

    public Vendor() {
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
