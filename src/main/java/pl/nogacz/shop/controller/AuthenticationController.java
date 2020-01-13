package pl.nogacz.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.shop.config.authentication.util.TokenUtil;
import pl.nogacz.shop.domain.user.User;
import pl.nogacz.shop.dto.authentication.AuthenticationRequestDto;
import pl.nogacz.shop.dto.authentication.AuthenticationResponseDto;
import pl.nogacz.shop.dto.authentication.RefreshTokenDto;
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

    @PostMapping(value = "/login")
    public AuthenticationResponseDto createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
        authenticationRequestDto = cleanService.cleanAuthenticationRequestDto(authenticationRequestDto);

        authenticate(authenticationRequestDto);

        final User user = userService.loadUserByUsername(authenticationRequestDto.getUsername());
        final String token = tokenUtil.generateToken(user);

        return new AuthenticationResponseDto(token, userMapper.mapUserToUserDto(user));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/login/refresh")
    public RefreshTokenDto refreshToken(@Autowired Authentication authentication) {
        final User user = userService.loadUserByUsername(authentication.getName());
        return new RefreshTokenDto(tokenUtil.generateToken(user));
    }

    @PutMapping(value = "/register")
    public UserDto register(@RequestBody RegisterRequestDto registerRequestDto) throws Exception {
        registerRequestDto = cleanService.cleanRegisterRequestDto(registerRequestDto);

        User user = userService.registerUser(registerRequestDto);
        return userMapper.mapUserToUserDto(user);
    }

    private void authenticate(AuthenticationRequestDto authenticationRequestDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new InvalidCredentialsException();
        }
    }
}
