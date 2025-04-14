package lu.karpychev.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "trkpt")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackPoint {
    @XmlAttribute
    private double lat;
    @XmlAttribute
    private double lon;
    @XmlElement(name = "ele")
    private double elevation;
    @XmlElement
    private String name;
}

