package lu.karpychev.trailfinder.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@RequiredArgsConstructor
public class Metadata {
    @XmlElement
    private String name;
    @XmlElement
    private TrackAuthor author;
    @XmlElement
    private TrackLink link;


    public String getName() {
        return name;
    }

    public TrackAuthor getAuthor() {
        return author;
    }

    public TrackLink getLink() {
        return link;
    }
}
