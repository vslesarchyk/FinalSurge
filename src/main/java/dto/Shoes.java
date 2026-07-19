package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Shoes {
    @Builder.Default
    private String shoeName="Nike";
    @Builder.Default
    private String brand="Nike";
    @Builder.Default
    private String model="Cortez";
    @Builder.Default
    private String cost="200";
    @Builder.Default
    private String datePurchased="07/15/2026";
    @Builder.Default
    private String size="25";
    @Builder.Default
    private String startDistance="300";
    @Builder.Default
    private String startDistanceType="km";
    @Builder.Default
    private String alertDistance="200";
    @Builder.Default
    private String alertDistanceType="km";
    @Builder.Default
    private String notes="successful test";
}
