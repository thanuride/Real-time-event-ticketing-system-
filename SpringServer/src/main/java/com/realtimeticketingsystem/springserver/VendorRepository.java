package com.realtimeticketingsystem.springserver;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends CrudRepository <Vendor,Integer> {
}
