package pl.nogacz.shop.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.nogacz.shop.service.user.UserValidService;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Hasło jest zbyt krótkie. Musi mieć minimum "+ UserValidService.MIN_PASSWORD_LENGTH + " znaków!")
public class PasswordTooShortException extends Exception {}
