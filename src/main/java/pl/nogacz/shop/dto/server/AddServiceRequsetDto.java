package pl.nogacz.shop.dto.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddServiceRequsetDto {
    @NotNull
    private Long serverId;

    @NotNull
    private String name;

    @NotNull
    private String flags;

    @NotNull
    private Long priceId;
}
