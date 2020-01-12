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
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .accountNonLocked(user.isAccountNonLocked())
                .accountNonExpired(user.isAccountNonExpired())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .enabled(user.isEnabled())
                .authorities(mapListUserRoleToListUserRoleDto(user.getAuthorities()))
                .build();
    }

    public UserRoleDto mapUserRoleToUserRoleDto(final UserRole userRole) {
        return UserRoleDto.builder()
                .id(userRole.getId())
                .role(userRole.getRole())
                .build();
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
