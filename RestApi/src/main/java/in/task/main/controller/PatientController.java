package in.task.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.task.main.entity.Patient;
import in.task.main.service.PatientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/addPatient")
	public Patient addPatient(@Valid @RequestBody Patient patient)
	{
		return patientService.addPatient(patient); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable int id)
	{
		try 
		{
			patientService.deletePatient(id);
			return ResponseEntity.noContent().build();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
			
		}
	}
}
