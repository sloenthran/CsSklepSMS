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
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "server_id")
    private Server server;

    @NotNull
    @Column(name = "flags")
    private String flags;

    @NotNull
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "price_id")
    private Number number;

    @OneToMany(
            targetEntity = PurchasedService.class,
            cascade = CascadeType.ALL,
            mappedBy = "service",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<PurchasedService> purchased = new ArrayList<>();
}
