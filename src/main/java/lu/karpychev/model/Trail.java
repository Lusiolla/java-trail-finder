package lu.karpychev.model;

import lombok.*;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trail {

    private Long id;
    private String title;
    private Duration duration;
    private Complexity complexity;
    private Type type;

}
