package lu.karpychev.trailfinder.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackAuthor {
    @XmlElement
    String name;
}
