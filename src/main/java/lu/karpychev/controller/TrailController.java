package lu.karpychev.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lu.karpychev.dao.TrailDao;
import lu.karpychev.model.Trail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;


@RestController
@Slf4j
@RequestMapping("/trails")
@AllArgsConstructor
public class TrailController {

   private final TrailDao trailStorage;


    @PostMapping
    public Trail create(@Valid @NotNull @RequestBody Trail trail) {
        return trailStorage.add(trail);
        //log.debug("Added a trail");
    }
    
}
