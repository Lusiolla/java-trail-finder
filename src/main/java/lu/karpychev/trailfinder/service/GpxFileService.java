package lu.karpychev.trailfinder.service;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.Data;
import lu.karpychev.trailfinder.model.GpxFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@Component
@Data
public class GpxFileService {

    private final String path = "GPXStorage/";

    public File marshal(GpxFile trackFile) throws JAXBException, IOException {

        File file = new File(path + trackFile.getMetadata().getName() + ".gpx");

        JAXBContext context = JAXBContext.newInstance(GpxFile.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(trackFile, file);

        return file;
    }

    public GpxFile unmarshalTrack(MultipartFile file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(GpxFile.class);
        return (GpxFile) context.createUnmarshaller()
                .unmarshal(new InputStreamReader(file.getInputStream()));
    }
}
