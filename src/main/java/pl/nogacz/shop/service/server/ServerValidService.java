package pl.nogacz.shop.service.server;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.domain.user.User;
import pl.nogacz.shop.exception.validation.BadIpException;
import pl.nogacz.shop.exception.validation.BadServerIdException;
import pl.nogacz.shop.exception.validation.IpExistsException;
import pl.nogacz.shop.repository.server.ServerRepository;

import javax.transaction.Transactional;
import java.util.regex.Pattern;

@Service
@Transactional
@AllArgsConstructor
public class ServerValidService {
    private ServerRepository serverRepository;

    public void validIp(String ip) throws Exception {
        Pattern pattern = Pattern.compile(
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5]):" +
                "([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$"
        );

        if(!pattern.matcher(ip).matches()) {
            throw new BadIpException();
        }

        if(serverRepository.existsByIp(ip)) {
            throw new IpExistsException();
        }
    }

    public void validServerOwner(final User user, final Server server) throws Exception {
        if(!server.getUser().getId().equals(user.getId())) {
            throw new BadServerIdException();
        }
    }
}
