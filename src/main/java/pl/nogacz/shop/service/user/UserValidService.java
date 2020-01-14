package pl.nogacz.shop.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nogacz.shop.exception.validation.EmailExistException;
import pl.nogacz.shop.exception.validation.PasswordIsNotThisSameException;
import pl.nogacz.shop.exception.validation.PasswordTooShortException;
import pl.nogacz.shop.exception.validation.UsernameExistException;
import pl.nogacz.shop.repository.user.UserRepository;
import pl.nogacz.shop.util.EmailValidate;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserValidService {
    public static final int MIN_PASSWORD_LENGTH = 10;
    private UserRepository userRepository;
    private EmailValidate emailValidate;

    public void validEmail(String email) throws Exception {
        this.emailValidate.validEmail(email);

        if(this.userRepository.existsByEmail(email)) {
            throw new EmailExistException();
        }
    }

    public void validPassword(String password, String passwordCheck) throws Exception {
        if(!password.contains(passwordCheck) || password == null || passwordCheck == null) {
            throw new PasswordIsNotThisSameException();
        }

        validPassword(password);
    }

    public void validPassword(String password) throws Exception {
        if(password.length() < MIN_PASSWORD_LENGTH) {
            throw new PasswordTooShortException();
        }
    }

    public void validUsername(String username) throws Exception {
        if(this.userRepository.existsByUsername(username)) {
            throw new UsernameExistException();
        }
    }
}
