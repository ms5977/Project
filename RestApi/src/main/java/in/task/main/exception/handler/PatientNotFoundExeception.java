package in.task.main.exception.handler;

public class PatientNotFoundExeception extends RuntimeException{
	
	public PatientNotFoundExeception(String message)
	{
		super(message);
	}
}
