package pl.nogacz.shop.service.authentication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nogacz.shop.dto.authentication.AuthenticationRequestDto;
import pl.nogacz.shop.dto.authentication.RegisterRequestDto;
import pl.nogacz.shop.util.HtmlClean;

@Service
@AllArgsConstructor
public class AuthenticationCleanService {
    private HtmlClean clean;

    public RegisterRequestDto cleanRegisterRequestDto(final RegisterRequestDto registerRequestDto) {
        registerRequestDto.setEmail(
                this.clean.cleanText(registerRequestDto.getEmail())
        );

        registerRequestDto.setPassword(
                this.clean.cleanText(registerRequestDto.getPassword())
        );

        registerRequestDto.setPasswordCheck(
                this.clean.cleanText(registerRequestDto.getPasswordCheck())
        );

        registerRequestDto.setUsername(
                this.clean.cleanText(registerRequestDto.getUsername())
        );

        return registerRequestDto;
    }

    public AuthenticationRequestDto cleanAuthenticationRequestDto(final AuthenticationRequestDto authenticationRequestDto) {
        authenticationRequestDto.setPassword(
                this.clean.cleanText(authenticationRequestDto.getPassword())
        );

        authenticationRequestDto.setUsername(
                this.clean.cleanText(authenticationRequestDto.getUsername())
        );

        return authenticationRequestDto;
    }
}
