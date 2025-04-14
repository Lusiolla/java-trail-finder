package lu.karpychev.service;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lu.karpychev.model.Track;
import lu.karpychev.model.TrackFile;
import lu.karpychev.model.TrackPoint;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@Component
public class TrackService {

    public File marshal(Track track) throws JAXBException, IOException {

        File file = new File("/track.xml");

        JAXBContext context = JAXBContext.newInstance(Track.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(track, file);

        return file;
    }

    public TrackFile unmarshalTrack(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            JAXBContext context = JAXBContext.newInstance(TrackFile.class);
            return (TrackFile) context.createUnmarshaller()
                    .unmarshal(is);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обработке GPX-файла", e);
        }
    }

    public TrackPoint unmarshalTrackPoint(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            JAXBContext context = JAXBContext.newInstance(TrackPoint.class);
            return (TrackPoint) context.createUnmarshaller()
                    .unmarshal(is);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обработке GPX-файла", e);
        }
    }


}
