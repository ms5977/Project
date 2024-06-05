package in.task.main.service;

import java.util.List;

import in.task.main.entity.Doctor;
import in.task.main.entity.Doctor.City;
import in.task.main.entity.Doctor.Speciality;

public interface DoctorService {
	
	public Doctor addDoctor(Doctor doctor);
	public void deleteDoctor(int id);
	public List<Doctor>findDoctor(City city,Speciality speciality);

}
