package pl.nogacz.shop.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Rola użytkownika nie znaleziona")
public class UserRoleNotFoundException extends Exception {}
