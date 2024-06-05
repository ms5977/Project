package in.task.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.task.main.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
