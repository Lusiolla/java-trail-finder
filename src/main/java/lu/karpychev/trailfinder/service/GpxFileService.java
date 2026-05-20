package lu.karpychev.trailfinder.service;

import jakarta.xml.bind.JAXBException;
import lu.karpychev.trailfinder.model.GpxFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public interface GpxFileService {

    UUID addGpxFileToDatabase(MultipartFile file) throws JAXBException, IOException;

    GpxFile getGpxFileByTrackId(UUID trackId);

}
