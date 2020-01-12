package pl.nogacz.shop.service.server;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nogacz.shop.dto.server.AddServerRequestDto;
import pl.nogacz.shop.util.HtmlClean;

@Service
@AllArgsConstructor
public class ServerCleanService {
    private HtmlClean htmlClean;

    public AddServerRequestDto cleanAddServerRequestDto(final AddServerRequestDto addServerRequestDto) {
        addServerRequestDto.setServerName(
                htmlClean.cleanText(addServerRequestDto.getServerName())
        );

        addServerRequestDto.setIp(
                htmlClean.cleanText(addServerRequestDto.getIp())
        );

        return addServerRequestDto;
    }
}
