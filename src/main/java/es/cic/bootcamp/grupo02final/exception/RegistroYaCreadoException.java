package es.cic.bootcamp.grupo02final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegistroYaCreadoException extends RuntimeException {

	public RegistroYaCreadoException(String message) {
		super(message);
	}

}
