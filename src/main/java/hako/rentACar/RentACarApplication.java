package hako.rentACar;

import java.util.HashMap;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hako.rentACar.core.utilities.exceptions.BusinessException;
import hako.rentACar.core.utilities.exceptions.ProblemDetails;
import hako.rentACar.core.utilities.exceptions.ValidationProblemDetails;
import hako.rentACar.core.utilities.results.DataResult;
import hako.rentACar.core.utilities.results.ErrorDataResult;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	// Execption handle ederken problem detail sinifi kullanilmali mi veya nasil kullanilmali
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public DataResult<ProblemDetails> handleBusinessException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return new ErrorDataResult<ProblemDetails>(businessException.getMessage(), problemDetails);
	}

	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public DataResult<ProblemDetails> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		ValidationProblemDetails problemDetails = new ValidationProblemDetails();
		problemDetails.setMessage("VALIDATION.EXCEPTION");
		problemDetails.setValidationErrors(new HashMap<String, String>());

		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			problemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return new ErrorDataResult<ProblemDetails>("VALIDATION.EXCEPTION", problemDetails);
	}

	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
