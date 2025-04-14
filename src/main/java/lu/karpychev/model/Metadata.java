package lu.karpychev.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.sql.Time;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {
    @XmlElement
    private String name;
    @XmlElement
    private Author author;
    @XmlElement
    private Link link;
}
