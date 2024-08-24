package com.main.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.entity.Supplier;
import com.main.exception.SupplierNotFoundException;
import com.main.repository.SupplierRepository;
import com.main.services.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public Supplier createSupplier(Supplier supplier) {
		Supplier savedSupplier = supplierRepository.save(supplier);
		return savedSupplier;
	}

	@Override
	public List<Supplier> addMultipleSupplier(List<Supplier> suppliers) {
		List<Supplier> saveSuppliers = supplierRepository.saveAll(suppliers);
		return saveSuppliers;
	}

	@Override
	public List<Supplier> searchSupplier(String location, String natureOfBusiness, String manufacturingProcess,Integer PageNumber,Integer PageSize) {
		Pageable pageable=PageRequest.of(PageNumber, PageSize);
		Page<Supplier> search = supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(location, natureOfBusiness, manufacturingProcess,pageable);
		List<Supplier> listSuppliers = search.getContent();
		if (listSuppliers.isEmpty()) {
			throw new SupplierNotFoundException("No Supplier Found For Given Data");
		}
		return listSuppliers;
	}

}
