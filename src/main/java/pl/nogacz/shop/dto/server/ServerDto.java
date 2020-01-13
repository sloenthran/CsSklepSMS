package pl.nogacz.shop.dto.server;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerDto {
    private Long id;
    private String name;
    private String ip;
}
