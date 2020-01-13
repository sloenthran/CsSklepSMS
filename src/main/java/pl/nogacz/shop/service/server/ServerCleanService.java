package pl.nogacz.shop.service.server;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nogacz.shop.dto.server.AddServerRequestDto;
import pl.nogacz.shop.dto.server.AddServiceRequsetDto;
import pl.nogacz.shop.util.HtmlClean;

@Service
@AllArgsConstructor
public class ServerCleanService {
    private HtmlClean htmlClean;

    public AddServerRequestDto cleanAddServerRequestDto(final AddServerRequestDto addServerRequestDto) {
        addServerRequestDto.setName(
                htmlClean.cleanText(addServerRequestDto.getName())
        );

        addServerRequestDto.setIp(
                htmlClean.cleanText(addServerRequestDto.getIp())
        );

        return addServerRequestDto;
    }

    public AddServiceRequsetDto cleanAddServiceRequestDto(final AddServiceRequsetDto addServiceRequsetDto) {
        addServiceRequsetDto.setName(
                htmlClean.cleanText(addServiceRequsetDto.getName())
        );

        return addServiceRequsetDto;
    }
}
