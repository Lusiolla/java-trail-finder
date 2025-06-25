package lu.karpychev.trailfinder.service.impl;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.Data;
import lu.karpychev.trailfinder.dao.GpxFileDao;
import lu.karpychev.trailfinder.model.GpxFile;
import lu.karpychev.trailfinder.service.GpxFileService;
import lu.karpychev.trailfinder.service.MetadataService;
import lu.karpychev.trailfinder.service.TrackService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
@Component
@Data
public class GpxFileServiceImpl implements GpxFileService {

    private final String path = "GPXStorage/";

    private final TrackService trackService;
    private final MetadataService metadataService;
    private final GpxFileDao gpxFileDao;


    public void addGpxFileToDatabase(MultipartFile file) throws JAXBException, IOException {
        GpxFile gpxFile = unmarshalGpxFile(file);

        gpxFile.setTrack(trackService.createTrack(gpxFile.getTrack()));
        long metadataId = metadataService.addMetadata(gpxFile.getMetadata(), gpxFile.getTrack().getId());

        gpxFileDao.add(gpxFile, metadataId);
    }

    @Override
    public File getGpxFileByTrackId(UUID trackId) throws JAXBException, IOException {
        return marshal(new GpxFile()); // ещё не реализован
    }


    private File marshal(GpxFile gpxFile) throws JAXBException, IOException {

        File file = new File(path + gpxFile.getMetadata().getName() + ".gpx");

        JAXBContext context = JAXBContext.newInstance(GpxFile.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(gpxFile, file);

        return file;
    }


    private GpxFile unmarshalGpxFile(MultipartFile file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(GpxFile.class);
        return (GpxFile) context.createUnmarshaller()
                .unmarshal(new InputStreamReader(file.getInputStream()));
    }

}

