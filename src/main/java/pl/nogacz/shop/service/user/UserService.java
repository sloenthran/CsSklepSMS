package pl.nogacz.shop.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.nogacz.shop.domain.user.Role;
import pl.nogacz.shop.domain.user.User;
import pl.nogacz.shop.domain.user.UserRole;
import pl.nogacz.shop.dto.authentication.RegisterRequestDto;
import pl.nogacz.shop.dto.user.UserChangePasswordDto;
import pl.nogacz.shop.exception.validation.BadOldPasswordException;
import pl.nogacz.shop.repository.user.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserValidService userValidService;
    private UserRoleService userRoleService;

    public User loadUserByUsername(final String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public User registerUser(final RegisterRequestDto registerRequestDto) throws Exception {
        this.userValidService.validUsername(registerRequestDto.getUsername());
        this.userValidService.validEmail(registerRequestDto.getEmail());
        this.userValidService.validPassword(registerRequestDto.getPassword(), registerRequestDto.getPasswordCheck());

        UserRole userRole = this.userRoleService.loadUserRoleByRole(Role.USER);
        List<UserRole> authorities = new ArrayList<>();
        authorities.add(userRole);

        User user = User.builder()
                .authorities(authorities)
                .username(registerRequestDto.getUsername())
                .password(this.passwordEncoder.encode(registerRequestDto.getPassword()))
                .email(registerRequestDto.getEmail())
                .accountExpiredTime(LocalDateTime.now())
                .build();

        return this.saveUser(user);
    }

    public User saveUser(final User user) {
        return this.userRepository.save(user);
    }

    public boolean changePassword(String username, final UserChangePasswordDto userChangePasswordDto) throws Exception {
        this.userValidService.validPassword(userChangePasswordDto.getNewPassword());

        User user = this.loadUserByUsername(username);

        if(!passwordEncoder.matches(userChangePasswordDto.getOldPassword(), user.getPassword())) {
            throw new BadOldPasswordException();
        }

        user.setPassword(this.passwordEncoder.encode(userChangePasswordDto.getNewPassword()));
        this.saveUser(user);

        return true;
    }
}
