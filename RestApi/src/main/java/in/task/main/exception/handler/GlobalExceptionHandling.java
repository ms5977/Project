package in.task.main.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandling 
{
	@ExceptionHandler(PatientNotFoundExeception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String>handleResourceNotFoundException(PatientNotFoundExeception e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
	
	@ExceptionHandler(InvalidCityException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String>handleInvalidCityException(InvalidCityException ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
 