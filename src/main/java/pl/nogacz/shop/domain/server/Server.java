package pl.nogacz.shop.domain.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nogacz.shop.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "servers")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "ip")
    private String ip;

    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            targetEntity = Service.class,
            cascade = CascadeType.ALL,
            mappedBy = "server",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Service> services = new ArrayList<>();
}
