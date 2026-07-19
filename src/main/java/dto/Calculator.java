package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Calculator {
    @Builder.Default
    private final String hours="1";
    @Builder.Default
    private final String minutes="10";
    @Builder.Default
    private final String seconds="20";
}
