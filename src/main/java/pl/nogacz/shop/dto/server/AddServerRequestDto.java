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
public class AddServerRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String ip;
}
