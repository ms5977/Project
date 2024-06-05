package in.task.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.task.main.entity.Doctor;
import in.task.main.entity.Doctor.City;
import in.task.main.entity.Doctor.Speciality;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	List<Doctor> findByCityAndSpeciality(City city, Speciality speciality);
}
