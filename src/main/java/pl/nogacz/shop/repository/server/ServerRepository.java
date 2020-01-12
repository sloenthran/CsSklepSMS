package pl.nogacz.shop.repository.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nogacz.shop.domain.server.Server;
import pl.nogacz.shop.domain.user.User;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    boolean existsByIp(String ip);
    List<Server> findAllByUserEqualsOrderById(User user);
}
