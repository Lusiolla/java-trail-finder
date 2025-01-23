package lu.karpychev.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trail {

    private Long id;
    private String title;
    private String description;
    //private Duration duration;
    //private Complexity complexity;
    //private Type type;
    @JsonDeserialize(using = GeometryDeserializer.class)
    @JsonSerialize(using = GeometrySerializer.class)
    private Geometry points;

}
