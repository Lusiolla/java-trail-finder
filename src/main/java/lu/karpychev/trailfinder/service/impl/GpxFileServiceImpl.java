package lu.karpychev.trailfinder.service.impl;

import jakarta.xml.bind.JAXBException;
import lombok.Data;
import lu.karpychev.trailfinder.dao.GpxFileDao;
import lu.karpychev.trailfinder.exception.ObjectNotFoundException;
import lu.karpychev.trailfinder.mapper.XmlMapper;
import lu.karpychev.trailfinder.model.GpxFile;
import lu.karpychev.trailfinder.service.GpxFileService;
import lu.karpychev.trailfinder.service.MetadataService;
import lu.karpychev.trailfinder.service.TrackService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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


    public UUID addGpxFileToDatabase(MultipartFile file) throws JAXBException, IOException {
        GpxFile gpxFile = XmlMapper.unmarshalGpxFile(file);

        gpxFile.setTrack(trackService.createTrack(gpxFile.getTrack()));
        long metadataId = metadataService.addMetadata(gpxFile.getMetadata(), gpxFile.getTrack().getId());

        gpxFileDao.add(gpxFile, metadataId);

        return gpxFile.getTrack().getId();
    }

    @Override
    public GpxFile getGpxFileByTrackId(UUID trackId) {
        return gpxFileDao.findByIdTrack(trackId).orElseThrow(() -> new ObjectNotFoundException("GPXFile", trackId));
    }

}


