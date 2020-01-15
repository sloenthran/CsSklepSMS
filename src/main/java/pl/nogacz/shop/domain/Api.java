package pl.nogacz.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nogacz.shop.domain.api.sms.Pukawka;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "api")
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @OneToOne(
            targetEntity = Pukawka.class,
            cascade = CascadeType.ALL,
            mappedBy = "api",
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "pukawka")
    private Pukawka pukawka;
}
