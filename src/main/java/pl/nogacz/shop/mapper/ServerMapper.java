package pl.nogacz.shop.mapper;

import org.springframework.stereotype.Component;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.domain.server.Service;
import pl.nogacz.shop.dto.server.ServerDto;
import pl.nogacz.shop.dto.server.ServiceDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServerMapper {
    public ServerDto mapServerToServerDto(final Server server) {
        return ServerDto.builder()
                .id(server.getId())
                .ip(server.getIp())
                .serverName(server.getServerName())
                .build();
    }

    public List<ServerDto> mapListServerToListServerDto(final List<Server> servers) {
        return servers.stream()
                .map(this::mapServerToServerDto)
                .collect(Collectors.toList());
    }

    public ServiceDto mapServiceToServiceDto(final Service service) {
        return ServiceDto.builder()
                .id(service.getId())
                .serverId(service.getServer().getId())
                .name(service.getName())
                .flag(service.getFlag())
                .build();
    }
}
