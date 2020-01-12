package pl.nogacz.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.dto.server.AddServerRequestDto;
import pl.nogacz.shop.dto.server.ServerDto;
import pl.nogacz.shop.mapper.ServerMapper;
import pl.nogacz.shop.service.server.ServerCleanService;
import pl.nogacz.shop.service.server.ServerService;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping(
        value = "/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ServerController {
    private ServerService serverService;
    private ServerCleanService serverCleanService;
    private ServerMapper serverMapper;

    @PutMapping("/server")
    public ServerDto addServer(@Autowired Authentication authentication, @RequestBody AddServerRequestDto addServerRequestDto) throws Exception {
        addServerRequestDto = serverCleanService.cleanAddServerRequestDto(addServerRequestDto);

        Server server = serverService.addServer(authentication.getName(), addServerRequestDto);

        return serverMapper.mapServerToServerDto(server);
    }
}
