package pl.nogacz.shop.repository.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nogacz.shop.domain.server.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}