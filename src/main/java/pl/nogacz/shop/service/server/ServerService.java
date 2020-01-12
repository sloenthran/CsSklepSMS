package pl.nogacz.shop.service.server;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.domain.user.User;
import pl.nogacz.shop.dto.server.AddServerRequestDto;
import pl.nogacz.shop.repository.server.ServerRepository;
import pl.nogacz.shop.service.user.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ServerService {
    private ServerRepository serverRepository;
    private UserService userService;
    private ServerValidService serverValidService;

    public Server addServer(String username, final AddServerRequestDto addServerRequestDto) throws Exception {
        User user = userService.loadUserByUsername(username);
        serverValidService.validIp(addServerRequestDto.getIp());

        Server server = Server.builder()
                .serverName(addServerRequestDto.getServerName())
                .ip(addServerRequestDto.getIp())
                .user(user)
                .build();

        return serverRepository.save(server);
    }

    public List<Server> getServers(String username) {
        User user = userService.loadUserByUsername(username);

        return serverRepository.findAllByUserEqualsOrderById(user);
    }

}
