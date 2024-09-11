package br.com.escconsulting.exception.handler;

import br.com.escconsulting.dto.ErrorRespondeDTO;
import br.com.escconsulting.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResponseEntityExceptionHandlerImpl extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logger.error("400 Status Code", ex);
		final BindingResult result = ex.getBindingResult();
		Locale locale = request.getLocale(); // Pega o Locale da requisição

		String error = result.getAllErrors().stream().map(e -> {
			String errorMessage = messageSource.getMessage(e, locale); // Traduz a mensagem de erro com base no Locale
			if (e instanceof FieldError) {
				return ((FieldError) e).getField() + ": " + errorMessage; // Campo específico
			} else {
				return e.getObjectName() + ": " + errorMessage; // Objeto de erro
			}
		}).collect(Collectors.joining(", "));

		return handleExceptionInternal(ex, new ErrorRespondeDTO(error), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
		Locale locale = request.getLocale(); // Pega o Locale da requisição
		String errorMessage = messageSource.getMessage(ex.getMessageKey(), null, locale);
		return new ResponseEntity<>(new ErrorRespondeDTO(errorMessage), HttpStatus.BAD_REQUEST);
	}
}