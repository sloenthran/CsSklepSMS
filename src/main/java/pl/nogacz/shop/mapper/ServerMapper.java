package pl.nogacz.shop.mapper;

import org.springframework.stereotype.Component;
import pl.nogacz.shop.domain.server.Price;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.domain.server.Service;
import pl.nogacz.shop.dto.server.PriceDto;
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
                .price(mapPriceToPriceDto(service.getPrice()))
                .build();
    }

    public List<ServiceDto> mapListServiceToListServiceDto(final List<Service> services) {
        return services.stream()
                .map(this::mapServiceToServiceDto)
                .collect(Collectors.toList());
    }

    public PriceDto mapPriceToPriceDto(final Price price) {
        return PriceDto.builder()
                .id(price.getId())
                .number(price.getNumber())
                .value(price.getValue())
                .description(price.getDescription())
                .build();
    }

    public List<PriceDto> mapListPriceToListPriceDto(final List<Price> prices) {
        return prices.stream()
                .map(this::mapPriceToPriceDto)
                .collect(Collectors.toList());
    }
}
