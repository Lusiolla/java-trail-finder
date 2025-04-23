package lu.karpychev.trailfinder.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@XmlRootElement(name = "trkpt")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@RequiredArgsConstructor
public class TrackPoint {
    @XmlAttribute
    private double lat;
    @XmlAttribute
    private double lon;
    @XmlElement(name = "ele")
    private double elevation;
}

