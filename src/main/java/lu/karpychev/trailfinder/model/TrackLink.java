package lu.karpychev.trailfinder.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "link")
@AllArgsConstructor
@RequiredArgsConstructor
public class TrackLink {

    @XmlAttribute(name = "href")
    private String URL;

   /* public String getURL() {
        if (URL == null) {
            return null;
        } else {
            return URL;
        }

    }*/
}
