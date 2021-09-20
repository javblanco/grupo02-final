package es.cic.bootcamp.grupo02final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IdNoValidoException extends RuntimeException {

	public IdNoValidoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IdNoValidoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
