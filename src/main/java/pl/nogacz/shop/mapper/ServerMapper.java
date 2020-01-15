package pl.nogacz.shop.mapper;

import org.springframework.stereotype.Component;
import pl.nogacz.shop.domain.server.Number;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.domain.server.Service;
import pl.nogacz.shop.dto.server.NumberDto;
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
                .name(server.getName())
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
                .flags(service.getFlags())
                .number(mapNumberToNumberDto(service.getNumber()))
                .build();
    }

    public List<ServiceDto> mapListServiceToListServiceDto(final List<Service> services) {
        return services.stream()
                .map(this::mapServiceToServiceDto)
                .collect(Collectors.toList());
    }

    public NumberDto mapNumberToNumberDto(final Number number) {
        return NumberDto.builder()
                .id(number.getId())
                .number(number.getNumber())
                .value(number.getValue())
                .description(number.getDescription())
                .build();
    }

    public List<NumberDto> mapListNumberToListNumberDto(final List<Number> numbers) {
        return numbers.stream()
                .map(this::mapNumberToNumberDto)
                .collect(Collectors.toList());
    }
}
