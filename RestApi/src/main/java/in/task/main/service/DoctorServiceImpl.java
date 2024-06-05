package in.task.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.task.main.entity.Doctor;
import in.task.main.entity.Doctor.City;
import in.task.main.entity.Doctor.Speciality;
import in.task.main.repository.DoctorRepository;
@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public Doctor addDoctor(Doctor doctor) {
		 return doctorRepository.save(doctor);
	}

	@Override
	public void deleteDoctor(int id) {
		doctorRepository.deleteById(id);
	}

	@Override
	public List<Doctor> findDoctor(City city, Speciality speciality) {
		return doctorRepository.findByCityAndSpeciality(city, speciality);
	}

}
