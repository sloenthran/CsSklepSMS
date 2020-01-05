package pl.nogacz.shop.scheduler.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.nogacz.shop.domain.user.Role;
import pl.nogacz.shop.domain.user.UserRole;
import pl.nogacz.shop.service.user.UserRoleService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class RoleScheduler {
    private UserRoleService userRoleService;

    @PostConstruct
    public void checkRoleExists() {
        for(Role role : Role.values()) {
            if(this.userRoleService.loadUserRoleByRoleWithoutException(role) == null) {
                UserRole userRole = new UserRole(null, role, new ArrayList<>());
                this.userRoleService.saveUserRole(userRole);
            }
        }
    }
}
