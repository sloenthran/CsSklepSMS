package pl.nogacz.shop.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import pl.nogacz.shop.exception.validation.BadEmailException;
import pl.nogacz.shop.exception.validation.EmailDisposableException;
import pl.nogacz.shop.exception.validation.EmailDomainNotFoundException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class EmailValidate {
    public void validEmail(String email) throws Exception {
        if(!EmailValidator.getInstance().isValid(email)) {
            throw new BadEmailException();
        }

        String[] emailSplit = email.split("@");

        List<String> disposableEmails = Files.readAllLines(Paths.get("DisposableEmail.txt"));

        if(disposableEmails.contains(emailSplit[1])) {
            throw new EmailDisposableException();
        }

        try {
            InetAddress.getByName(emailSplit[1]);
        } catch (UnknownHostException e) {
            throw new EmailDomainNotFoundException();
        }
    }
}