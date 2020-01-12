package pl.nogacz.shop.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Takie IP już istnieje w bazie!")
public class IpExistsException extends Exception {}
