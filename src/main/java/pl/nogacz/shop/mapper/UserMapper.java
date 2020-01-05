package pl.nogacz.shop.mapper;

import org.springframework.stereotype.Component;
import pl.nogacz.shop.domain.user.User;
import pl.nogacz.shop.domain.user.UserRole;
import pl.nogacz.shop.dto.user.UserDto;
import pl.nogacz.shop.dto.user.UserRoleDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto mapUserToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                this.mapListUserRoleToListUserRoleDto(user.getAuthorities())
        );
    }

    public UserRoleDto mapUserRoleToUserRoleDto(final UserRole userRole) {
        return new UserRoleDto(
                userRole.getId(),
                userRole.getRole()
        );
    }

    public List<UserRoleDto> mapListUserRoleToListUserRoleDto(final List<UserRole> userRoles) {
        return userRoles.stream()
                .map(this::mapUserRoleToUserRoleDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> mapListUserToListUserDto(final List<User> users) {
        return users.stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }
}
