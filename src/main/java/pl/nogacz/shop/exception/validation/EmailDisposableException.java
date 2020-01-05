package pl.nogacz.shop.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "To jest tymczasowy adres email")
public class EmailDisposableException extends Exception {}
