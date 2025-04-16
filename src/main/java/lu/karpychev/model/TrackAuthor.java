package lu.karpychev.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement (name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackAuthor {
    @XmlElement
    String name;
}
