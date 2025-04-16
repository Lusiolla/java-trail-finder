package lu.karpychev.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {
    @XmlElement
    private String name;
    @XmlElement
    private TrackAuthor author;
    @XmlElement
    private TrackLink link;
}
