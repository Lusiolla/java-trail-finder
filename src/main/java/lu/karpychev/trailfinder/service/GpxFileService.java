package lu.karpychev.trailfinder.service;

import jakarta.xml.bind.JAXBException;
import lu.karpychev.trailfinder.model.GpxFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public interface GpxFileService {

    UUID addGpxFileToDatabase(MultipartFile file) throws JAXBException, IOException;

    GpxFile getGpxFileByTrackId(UUID trackId) throws JAXBException, IOException;

}
