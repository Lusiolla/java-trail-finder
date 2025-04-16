package lu.karpychev.controller;

import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import lu.karpychev.dao.TrackDao;
import lu.karpychev.model.*;
import lu.karpychev.service.GpxFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/trails")
@AllArgsConstructor
public class TrackController {

    private final TrackDao trailStorage;
    private final GpxFileService service;

    @RequestMapping("/tracks")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public File echoXmlTrack(@RequestParam ("file") MultipartFile file) throws JAXBException, IOException {
        return service.marshal(service.unmarshalTrack(file));
    }

    @RequestMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public User echoXmlUser(@RequestBody User user) {
        return user;
    }

}
