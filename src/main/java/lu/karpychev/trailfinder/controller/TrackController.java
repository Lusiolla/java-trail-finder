package lu.karpychev.trailfinder.controller;

import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.mapper.XmlMapper;
import lu.karpychev.trailfinder.model.GpxFile;
import lu.karpychev.trailfinder.model.Track;
import lu.karpychev.trailfinder.model.User;
import lu.karpychev.trailfinder.service.GpxFileService;
import lu.karpychev.trailfinder.service.TrackService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/trails")
@AllArgsConstructor
public class TrackController {

    private final TrackService trackService;
    private final GpxFileService gpxFileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/tracks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UUID addGpxFile(@RequestParam("file") MultipartFile file) throws JAXBException, IOException {
        return gpxFileService.addGpxFileToDatabase(file);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_XML_VALUE)
    public User echoXmlUser(@RequestBody User user) {
        return user;
    }


    @GetMapping("/distance/{lat}/{lon}")
    public TrackDto getNearestTrack(@PathVariable Double lat, @PathVariable Double lon) throws FileNotFoundException {
        return trackService.getNearestTrack(lat, lon);
    }

    @GetMapping(value = "/tracks/gpx/{trackId}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ByteArrayResource> getGpxFileById(
            @PathVariable UUID trackId
    ) throws JAXBException, FileNotFoundException {
        GpxFile gpxFile = gpxFileService.getGpxFileByTrackId(trackId);
        String fileName = gpxFile.getTrack().getName() + ".gpx";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileName + "\"")
                .body(XmlMapper.marshal(gpxFile));
    }

    @GetMapping(value = "/tracks/{trackId}")
    public Track getTrackById(@PathVariable UUID trackId) throws FileNotFoundException {
        return trackService.getTrackById(trackId);
    }
}
