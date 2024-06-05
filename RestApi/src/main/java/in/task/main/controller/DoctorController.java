package in.task.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.task.main.entity.Doctor;
import in.task.main.service.DoctorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;	
	
	@PostMapping("/add")
	public Doctor addDoctors(@Valid @RequestBody Doctor doctor)
	{
		return doctorService.addDoctor(doctor);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable int id)
	{
		
		try 
		{
			doctorService.deleteDoctor(id);
			return ResponseEntity.noContent().build();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
			
		}
	}
}
 