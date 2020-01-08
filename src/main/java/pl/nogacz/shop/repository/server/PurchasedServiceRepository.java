package pl.nogacz.shop.repository.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nogacz.shop.domain.server.PurchasedService;

@Repository
public interface PurchasedServiceRepository extends JpaRepository<PurchasedService, Long> {
}
