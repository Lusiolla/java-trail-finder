package lu.karpychev.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.*;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trk")
public class Track {
    private Long id;
    @XmlElement
    private String name;
    @XmlElement(name = "desc")
    private String description;
    @XmlElement
    private String type;
    @XmlElement(name = "trkseg")
    private TrackSegment segment;
}
