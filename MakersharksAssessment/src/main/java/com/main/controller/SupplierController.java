package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Supplier;
import com.main.services.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/supplier")
@Tag(name = "Supplier",description = "Supplier Management Api")
public class SupplierController 
{
	
	@Autowired
	private SupplierService supplierService;
	
//------------------------------------	Add one Supplier At a time:------------------------------------------------
	@Operation(summary = "For Adding a Single Supplier",
			   tags = {"/addSingleSupplier","post"}
			
			)
	@PostMapping("/addSingleSupplier")
	ResponseEntity<Supplier>createSingleSupplier(@Valid @RequestBody Supplier supplier)
	{
		Supplier createSupplier = supplierService.createSupplier(supplier);
		return new ResponseEntity<Supplier>(createSupplier,HttpStatus.OK);
	}
//	--------------------------------Add Multiple Supplier-----------------------------------------------------------
	@Operation(summary = "For Adding a Multiple  Supplier",
			   tags = {"/addMultipleSuppliers","post"}
			
			)
	@PostMapping("/addMultipleSuppliers")
	ResponseEntity<List<Supplier>>createMultipleSupplier(@Valid @RequestBody List<Supplier>suppliers)
	{
		List<Supplier> addMultipleSupplier = supplierService.addMultipleSupplier(suppliers);
		return new ResponseEntity<List<Supplier>>(addMultipleSupplier,HttpStatus.OK);
	}

//-----------------------------------	Search Supplier-------------------------------------------------------------
	@Operation(summary = "For Searching a Supplier",
			   tags = {"/query","Get"}
			
			)
	@GetMapping("/query")
	ResponseEntity<List<Supplier>>searchSupplier
			(
				@Parameter(description = "Provide the Location Of Supplier")@RequestParam String location,
				@Parameter(description = "Provide the Nature Of buisness")	@RequestParam String natureOfBusiness,
				@Parameter(description = "Provide the Nature Of ManFacturing Process")	@RequestParam String manufacturingProcess,
				@Parameter(description = "Provide the Page Number")	@RequestParam Integer pageNumber,
				@Parameter(description = "Provide the Page Size")	@RequestParam Integer pageSize
			)
	{
		List<Supplier> searchSupplier = supplierService.searchSupplier(location, natureOfBusiness, manufacturingProcess,pageNumber,pageSize);
		return new ResponseEntity<List<Supplier>>(searchSupplier,HttpStatus.FOUND);
	}








}
