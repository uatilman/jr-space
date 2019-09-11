package com.space.service;

import com.space.controller.ShipOrder;
import com.space.exceptions.IllegalShipIdException;
import com.space.exceptions.ShipNotFoundException;
import com.space.exceptions.ShipSaveFailedException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.repository.ShipSpecification;
import com.space.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public Ship save(Ship ship) {
        if (ship.getIsUsed() == null) ship.setIsUsed(false);
        validOrThrow(ship);
        updateRating(ship);
        return shipRepository.save(ship);
    }

    private void updateRating(Ship ship) {
        double draftResult = (80 * ship.getSpeed() * (ship.getIsUsed() ? 0.5 : 1)) / (new Date().getYear() + 1000 - ship.getProdDate().getYear() + 1);
        ship.setRating(new BigDecimal(draftResult).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
    }

    public void delete(String id) {
        Long idLong = getValidShipIdOrThrow(id);
        existByIdOrThrow(idLong);
        shipRepository.deleteById(idLong);
    }

    public Ship update(Ship modernShip, String id) {
        Long idLong = getValidShipIdOrThrow(id);
        existByIdOrThrow(idLong);
        Ship ship = findById(idLong);

        if (modernShip.getName() != null) ship.setName(modernShip.getName());
        if (modernShip.getPlanet() != null) ship.setPlanet(modernShip.getPlanet());
        if (modernShip.getShipType() != null) ship.setShipType(modernShip.getShipType());
        if (modernShip.getProdDate() != null) ship.setProdDate(modernShip.getProdDate());
        if (modernShip.getIsUsed() != null) ship.setIsUsed(modernShip.getIsUsed());
        if (modernShip.getSpeed() != null) ship.setSpeed(modernShip.getSpeed());
        if (modernShip.getCrewSize() != null) ship.setCrewSize(modernShip.getCrewSize());

        updateRating(ship);
        validOrThrow(ship);

        return shipRepository.save(ship);
    }

    public Ship findById(Long id) {
        return shipRepository.findById(id).orElse(null);
    }

    private void existByIdOrThrow(Long id) {
        boolean exist = shipRepository.existsById(id);
        if (!exist) throw new ShipNotFoundException();
    }

    private Long getValidShipIdOrThrow(String id) {
        Long idLong = Utils.getLongOrNull(id);
        if (idLong == null || idLong <= 0) throw new IllegalShipIdException();
        return idLong;
    }

    private boolean isValid(Ship ship) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Ship>> s = validator.validate(ship);
        return s.size() == 0;
    }

    private void validOrThrow(Ship ship) {
        if (!isValid(ship)) throw new ShipSaveFailedException();
    }

}
