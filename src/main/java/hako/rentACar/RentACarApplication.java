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

import hako.rentACar.core.utilities.results.ErrorDataResult;
import hako.rentACar.core.utilities.results.exceptions.BusinessException;
import hako.rentACar.core.utilities.results.exceptions.ValidationProblemDetails;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	// Execption handle ederken problem detail sinifi kullanilmali mi veya nasil kullanilmali
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorDataResult<BusinessException> handleBusinessException(BusinessException businessException) {
		System.out.println(businessException.getMessage());
		BusinessException businessEx = new BusinessException(businessException.getMessage());
		return new ErrorDataResult<BusinessException>(businessEx.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorDataResult<ValidationProblemDetails> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		ValidationProblemDetails problemDetails = new ValidationProblemDetails(new HashMap<String, String>());

		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			problemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return new ErrorDataResult<ValidationProblemDetails>("VALIDATION.EXCEPTION", problemDetails);
	}

	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
