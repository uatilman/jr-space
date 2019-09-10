package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/ships")
public class ShipRestController {

    private ShipService shipService;

    @Autowired
    public ShipRestController(ShipService shipService) {
        this.shipService = shipService;
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

}
