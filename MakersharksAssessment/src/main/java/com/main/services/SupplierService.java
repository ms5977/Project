package com.main.services;

import java.util.List;

import com.main.entity.Supplier;

public interface SupplierService
{
	public Supplier createSupplier(Supplier supplier);
	public List<Supplier>addMultipleSupplier(List<Supplier>suppliers);//I am using this because so that I can add 
																	  //multiple supplier at once rather than one by one
																		//through the postman while API testing

	public List<Supplier>searchSupplier(
			String location,
			String natureOfBusiness,
			String manufacturingProcess,
			Integer PageNumber,
			Integer PageSize
			
			);

}

