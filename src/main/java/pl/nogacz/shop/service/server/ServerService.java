package pl.nogacz.shop.service.server;

import lombok.AllArgsConstructor;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.domain.server.Service;
import pl.nogacz.shop.domain.user.User;
import pl.nogacz.shop.dto.server.AddServerRequestDto;
import pl.nogacz.shop.dto.server.AddServiceRequsetDto;
import pl.nogacz.shop.exception.validation.BadServerIdException;
import pl.nogacz.shop.repository.server.ServerRepository;
import pl.nogacz.shop.repository.server.ServiceRepository;
import pl.nogacz.shop.service.user.UserService;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
@AllArgsConstructor
public class ServerService {
    private ServerRepository serverRepository;
    private ServiceRepository serviceRepository;

    private UserService userService;
    private ServerValidService serverValidService;

    public Server addServer(final String username, final AddServerRequestDto addServerRequestDto) throws Exception {
        User user = userService.loadUserByUsername(username);
        serverValidService.validIp(addServerRequestDto.getIp());

        Server server = Server.builder()
                .serverName(addServerRequestDto.getServerName())
                .ip(addServerRequestDto.getIp())
                .user(user)
                .build();

        return serverRepository.save(server);
    }

    public Service addService(final String username, final AddServiceRequsetDto addServiceRequsetDto) throws Exception {
        User user = userService.loadUserByUsername(username);
        Server server = serverRepository.getOne(addServiceRequsetDto.getServerId());

        if(!server.getUser().getId().equals(user.getId())) {
            throw new BadServerIdException();
        }

        Service service = Service.builder()
                .server(server)
                .name(addServiceRequsetDto.getName())
                .flag(addServiceRequsetDto.getFlag())
                .build();

        return serviceRepository.save(service);
    }

    public List<Server> getServers(final String username) {
        User user = userService.loadUserByUsername(username);

        return serverRepository.findAllByUserEqualsOrderById(user);
    }

}
