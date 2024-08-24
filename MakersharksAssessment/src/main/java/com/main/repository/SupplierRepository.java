package com.main.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Supplier;
//@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> 
{
	
	Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcesses(
	        String location,
	        String natureOfBusiness,
	        String manufacturingProcesses,
	        Pageable pageable
	        
			);
	
}
//Retrieve a list of X number of manufacturer(s) in a given location, 
//with a specific nature of business, and the capability to perform 
//a specific manufacturing process.