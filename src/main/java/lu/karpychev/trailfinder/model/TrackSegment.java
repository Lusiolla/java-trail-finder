package lu.karpychev.trailfinder.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trkseg")
@AllArgsConstructor
@RequiredArgsConstructor
public class TrackSegment {
    @XmlElement(name = "trkpt")
    private List<TrackPoint> points;
}
