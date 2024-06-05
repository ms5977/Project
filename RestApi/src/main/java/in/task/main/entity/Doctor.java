package in.task.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name cannot be null")
	@Size(min = 3,message = "Name should be atleast 3 Character")
	private String name;
	
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email is not valid")
	private String email;
	
	@Enumerated(EnumType.STRING)
//	@NotBlank(message = "city cannot be null")
//	@Size(max = 20,message ="City should be at max 20 characters" )
	private City city;
	
	@NotBlank(message = "Phone Number Cannot be Null")
	@Size(min = 10,message = "Phone number should be at least 10 number")
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private Speciality speciality;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Speciality getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
	public enum City {
		DELHI,NOIDA,FARIDABAD
	}
	public enum Speciality{
		ORTHOPEDIC, GYNECOLOGY, DERMATOLOGY, ENT
	}
	
}
