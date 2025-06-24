package lu.karpychev.trailfinder.controller;

import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import lu.karpychev.trailfinder.dto.TrackDto;
import lu.karpychev.trailfinder.model.Track;
import lu.karpychev.trailfinder.model.User;
import lu.karpychev.trailfinder.service.GpxFileService;
import lu.karpychev.trailfinder.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @RequestMapping("/tracks")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addGpxFile(@RequestParam("file") MultipartFile file) throws JAXBException, IOException {
        gpxFileService.addGpxFileToDatabase(file);
    }

    @RequestMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public User echoXmlUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping("/distance/{lat}/{lon}")
    @ResponseBody
    @GetMapping
    public TrackDto getNearestTrack(@PathVariable Double lat, @PathVariable Double lon) throws FileNotFoundException {
        return trackService.getNearestTrack(lat, lon);
    }

    @RequestMapping("/tracks/gpx/{trackId}")
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public File getGpxFileById(@PathVariable UUID trackId) throws IOException, JAXBException {
        return gpxFileService.getGpxFileByTrackId(trackId);
    }

    @RequestMapping("/tracks/{trackId}")
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Track getTrackById(@PathVariable UUID trackId) throws FileNotFoundException {
        return trackService.getTrackById(trackId);
    }
}
