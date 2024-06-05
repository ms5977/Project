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
public class Patient 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name cannot be null")
	@Size(min = 3,message = "Name should be atleast 3 Character")
	private String name;
	
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Email is not valid")
	private String email;
	
	@NotBlank(message = "city cannot be null")
	@Size(max = 20,message ="City should be at max 20 characters" )
	private String city;
	
	@NotBlank(message = "Phone Number Cannot be Null")
	@Size(min = 10,message = "Phone number should be at least 10 number")
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private Symptom symptom;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Symptom getSymptom() {
		return symptom;
	}
	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public enum Symptom
	{
		ARTHRITIS, BACK_PAIN, TISSUE_INJURIES,
	    DYSMENORRHEA,
	    SKIN_INFECTION, SKIN_BURN,
	    EAR_PAIN
	}
}
