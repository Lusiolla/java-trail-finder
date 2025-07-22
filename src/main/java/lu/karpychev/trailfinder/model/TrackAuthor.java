package lu.karpychev.trailfinder.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@RequiredArgsConstructor
public class TrackAuthor {
    @XmlElement
    String name;
}
