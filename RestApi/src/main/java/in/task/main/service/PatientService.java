package in.task.main.service;

import in.task.main.entity.Patient;

public interface PatientService {
	public Patient addPatient(Patient patient);
	public void deletePatient(int id);
	public Patient getPatientById(int id);
}
