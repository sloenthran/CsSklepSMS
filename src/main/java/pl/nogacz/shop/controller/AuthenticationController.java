package pl.nogacz.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.shop.config.authentication.util.TokenUtil;
import pl.nogacz.shop.domain.user.User;
import pl.nogacz.shop.dto.authentication.AuthenticationRequestDto;
import pl.nogacz.shop.dto.authentication.AuthenticationResponseDto;
import pl.nogacz.shop.dto.authentication.RegisterRequestDto;
import pl.nogacz.shop.dto.user.UserDto;
import pl.nogacz.shop.exception.validation.InvalidCredentialsException;
import pl.nogacz.shop.mapper.UserMapper;
import pl.nogacz.shop.service.authentication.AuthenticationCleanService;
import pl.nogacz.shop.service.user.UserService;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping(
        value = "/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private TokenUtil tokenUtil;
    private UserService userService;
    private UserMapper userMapper;
    private AuthenticationCleanService cleanService;

    @PostMapping(value = "login")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
        authenticationRequestDto = cleanService.cleanAuthenticationRequestDto(authenticationRequestDto);

        this.authenticate(authenticationRequestDto);

        final UserDetails user = this.userService.loadUserByUsername(authenticationRequestDto.getUsername());
        final String token = this.tokenUtil.generateToken(user);

        return new AuthenticationResponseDto(token);
    }

    @PutMapping(value = "register")
    public UserDto register(@RequestBody RegisterRequestDto registerRequestDto) throws Exception {
        registerRequestDto = cleanService.cleanRegisterRequestDto(registerRequestDto);

        User user = this.userService.registerUser(registerRequestDto);
        return this.userMapper.mapUserToUserDto(user);
    }

    private void authenticate(AuthenticationRequestDto authenticationRequestDto) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new InvalidCredentialsException();
        }
    }
}
