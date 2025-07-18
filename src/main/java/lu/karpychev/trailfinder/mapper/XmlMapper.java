package lu.karpychev.trailfinder.mapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lu.karpychev.trailfinder.model.GpxFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class XmlMapper {
    public static GpxFile unmarshalGpxFile(MultipartFile file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(GpxFile.class);
        return (GpxFile) context.createUnmarshaller()
                .unmarshal(new InputStreamReader(file.getInputStream()));
    }

    public static ByteArrayResource marshal(GpxFile gpxFile) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(GpxFile.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        mar.marshal(gpxFile, out);

        return new ByteArrayResource(out.toByteArray());
    }
}
