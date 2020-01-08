package pl.nogacz.shop.domain.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "server_id")
    private Server server;

    @NotNull
    @Column(name = "flag")
    private String flag;

    @OneToMany(
            targetEntity = Price.class,
            cascade = CascadeType.ALL,
            mappedBy = "service",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Price> prices = new ArrayList<>();

    @OneToMany(
            targetEntity = PurchasedService.class,
            cascade = CascadeType.ALL,
            mappedBy = "service",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<PurchasedService> purchased = new ArrayList<>();
}
