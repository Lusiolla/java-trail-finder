package lu.karpychev.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "gpx", namespace = "http://www.topografix.com/GPX/1/1")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackFile {
    @XmlAttribute
    private String version;
    @XmlAttribute
    private String creator;
    @XmlAttribute(namespace = "http://www.w3.org/2001/XMLSchema-instance")
    private String schemaLocation;
    @XmlElement
    private Metadata metadata;
    @XmlElement(name = "trk")
    private List<Track> tracks;
}
