package pl.nogacz.shop.dto.server;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDto {
    private Long id;
    private Long number;
    private Double value;
    private String description;
}
