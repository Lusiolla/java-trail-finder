package lu.karpychev.controller;

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

@RestController
@Slf4j
@RequestMapping("/trails")
@AllArgsConstructor
public class TrailController {

    private final TrailDao trailStorage;

    @PostMapping
    public Trail create(@Valid @NotNull @RequestBody Trail trail) {
        log.debug("Added a trail");
        return trailStorage.add(trail);

    }

}
