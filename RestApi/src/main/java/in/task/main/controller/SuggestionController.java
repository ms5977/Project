package in.task.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.task.main.entity.Doctor;
import in.task.main.entity.Doctor.City;
import in.task.main.entity.Doctor.Speciality;
import in.task.main.entity.Patient;
import in.task.main.entity.Patient.Symptom;
import in.task.main.exception.handler.DoctorNotFoundException;
import in.task.main.exception.handler.InvalidCityException;
import in.task.main.exception.handler.PatientNotFoundExeception;
import in.task.main.service.DoctorService;
import in.task.main.service.PatientService;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/{patientId}")
	public ResponseEntity<?>suggestDoctor(@PathVariable int patientId)
	{
		Patient patient = patientService.getPatientById(patientId);
		System.out.println(patient);
		if (patient==null) {
			throw new PatientNotFoundExeception("Patient not found:"+patientId);
		}
		String patientCity= patient.getCity().toUpperCase();
        if (!isValidCity(patientCity))
        {
			throw new  InvalidCityException("We are still waiting to expand to your location");
		}
		
		Speciality speciality = suggestSpecializationDoctorAccordingToSymptom(patient.getSymptom());
		List<Doctor> doctors = doctorService.findDoctor(City.valueOf(patientCity),speciality);	
		if (doctors.isEmpty())
		{
			throw new DoctorNotFoundException("There is not any doctor present at your location for your symptom");
		}
		return ResponseEntity.ok(doctors);
		
	}
	
//---------------------------------------------	
	private Speciality suggestSpecializationDoctorAccordingToSymptom(Symptom symptom)
	{
		switch (symptom) {
		 case ARTHRITIS:
         case BACK_PAIN:
         case TISSUE_INJURIES:
        	 return Speciality.ORTHOPEDIC;
         case DYSMENORRHEA:
        	 return Speciality.GYNECOLOGY;
         case SKIN_INFECTION:
         case SKIN_BURN:
        	 return Speciality.DERMATOLOGY;
        
         case EAR_PAIN:
        	 return Speciality.ENT;
          

		default:
			throw new IllegalArgumentException("Unknown Symptom:"+symptom);
		}
	}
//---------------------------------------------	
	private boolean isValidCity(String city) {
	        for (City validCity : City.values()) {
	            System.out.println(validCity);
	        	if (validCity.name().equals(city)) {
	                return true;
	            }
	        }
	        return false;
	  }
}
