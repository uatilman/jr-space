package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.repository.ShipSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<Ship> findAll(
            String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed,
            Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating, String order,
            Integer pageNumber, Integer pageSize
    ) {
        return shipRepository.findAll(
                ShipSpecification.getShipSpecification(name, planet, shipType, after, before, isUsed, minSpeed,
                        maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating),
                PageRequest.of(pageNumber, pageSize, new Sort(ShipOrder.valueOf(order).getFieldName()))
        ).getContent();
    }

    public Long countAll(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                         Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating,
                         Double maxRating
    ) {
        return shipRepository.count(
                ShipSpecification.getShipSpecification(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                        minCrewSize, maxCrewSize, minRating, maxRating));
    }

}
