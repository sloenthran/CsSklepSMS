package pl.nogacz.shop.mapper;

import org.springframework.stereotype.Component;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.dto.server.ServerDto;

@Component
public class ServerMapper {
    public ServerDto mapServerToServerDto(final Server server) {
        return ServerDto.builder()
                .id(server.getId())
                .ip(server.getIp())
                .serverName(server.getServerName())
                .build();
    }
}
