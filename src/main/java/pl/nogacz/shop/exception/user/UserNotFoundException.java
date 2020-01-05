package pl.nogacz.shop.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Użytkownik nie znaleziony")
public class UserNotFoundException extends Exception {}
