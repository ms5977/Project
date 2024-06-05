package in.task.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.task.main.entity.Patient;
import in.task.main.repository.PatientRepository;

@Service
public class PatentServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Patient addPatient(Patient patient) {
		
		return patientRepository.save(patient);
	}

	@Override
	public void deletePatient(int id) {
		patientRepository.deleteById(id);
	}

	@Override
	public Patient getPatientById(int id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id).orElse(null);
	}

}
