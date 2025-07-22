package lu.karpychev.trailfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TrackDto {
    private UUID id;

    private String name;

    private String distanceMeters;
}
