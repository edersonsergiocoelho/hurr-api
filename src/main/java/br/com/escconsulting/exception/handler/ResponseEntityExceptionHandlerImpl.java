package br.com.escconsulting.exception.handler;

import br.com.escconsulting.dto.ErrorRespondeDTO;
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

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
		Locale locale = request.getLocale(); // Obter o Locale da requisição
		String errorMessage;

		if (ex instanceof MethodArgumentNotValidException) {
			// Tratar o caso específico de validação de argumento
			errorMessage = ((MethodArgumentNotValidException) ex).getBindingResult()
					.getAllErrors().stream()
					.map(e -> {
						String message = messageSource.getMessage(e, locale); // Traduz a mensagem de erro
						if (e instanceof FieldError) {
							return ((FieldError) e).getField() + ": " + message;
						} else {
							return e.getObjectName() + ": " + message;
						}
					}).collect(Collectors.joining(", "));
		} else if (ex instanceof RuntimeException) {
			// Tratamento genérico para RuntimeException com chave personalizada
			errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
		} else {
			// Tratamento para exceções não mapeadas
			errorMessage = "Erro inesperado. Tente novamente mais tarde.";
		}

		return new ResponseEntity<>(new ErrorRespondeDTO(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}