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
@Entity(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "number")
    private Long number;

    @NotNull
    @Column(name = "value")
    private Double value;

    @NotNull
    @Column(name = "description")
    private String description;

    @OneToMany(
            targetEntity = Service.class,
            cascade = CascadeType.ALL,
            mappedBy = "price",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Service> services = new ArrayList<>();
}
