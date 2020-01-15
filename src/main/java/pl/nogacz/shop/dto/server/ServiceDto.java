package pl.nogacz.shop.dto.server;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDto {
    private Long id;
    private String name;
    private Long serverId;
    private NumberDto number;
    private String flags;
}
