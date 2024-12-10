package lu.karpychev.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class Point {

    private Long id;
    @NotBlank
    @NotNull
    private Float lat;
    @NotBlank
    @NotNull
    private Float lon;

}
