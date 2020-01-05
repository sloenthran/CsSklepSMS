package pl.nogacz.shop.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Błędny adres email")
public class BadEmailException extends Exception {}
