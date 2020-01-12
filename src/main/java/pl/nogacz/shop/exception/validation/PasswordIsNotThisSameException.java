package pl.nogacz.shop.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Hasła nie są takie same!")
public class PasswordIsNotThisSameException extends Exception {}
