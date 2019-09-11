package com.space.controller;

import com.space.exceptions.IllegalShipIdException;
import com.space.exceptions.ShipNotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("rest/ships")
public class ShipRestController {

    private ShipService shipService;

    @Autowired
    public ShipRestController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship findById(@PathVariable Long id) {
        if (id <= 0) throw new IllegalShipIdException();

        Ship ship = shipService.findById(id);
        if (ship == null) throw new ShipNotFoundException();
        return shipService.findById(id);
    }

    @GetMapping
    public List<Ship> viewAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String planet,
            @RequestParam(required = false) ShipType shipType,

            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,

            @RequestParam(required = false) Boolean isUsed,

            @RequestParam(required = false) Double minSpeed,
            @RequestParam(required = false) Double maxSpeed,

            @RequestParam(required = false) Integer minCrewSize,
            @RequestParam(required = false) Integer maxCrewSize,

            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating,

            @RequestParam(defaultValue = "ID") String order,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "3") Integer pageSize
    ) {
        return shipService.findAll(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating, order, pageNumber, pageSize);
    }

    @GetMapping("count")
    public Long countAll(

            @RequestParam(required = false) String name,
            @RequestParam(required = false) String planet,
            @RequestParam(required = false) ShipType shipType,

            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,

            @RequestParam(required = false) Boolean isUsed,

            @RequestParam(required = false) Double minSpeed,
            @RequestParam(required = false) Double maxSpeed,

            @RequestParam(required = false) Integer minCrewSize,
            @RequestParam(required = false) Integer maxCrewSize,

            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating
    ) {
        return shipService.countAll(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Ship save(@Valid @RequestBody Ship ship) {
        return shipService.save(ship);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship update(
            @RequestBody(required = false) Ship ship,
            @PathVariable String id
    ) {
        return shipService.update(ship, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        shipService.delete(id);
    }

}
