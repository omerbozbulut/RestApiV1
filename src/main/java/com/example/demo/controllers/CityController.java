package com.example.demo.controllers;

import com.example.demo.entities.City;
import com.example.demo.exception.CityAlreadyExistsException;
import com.example.demo.exception.CityNotFoundException;
import com.example.demo.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getAll(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(cityService.getAll(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id){
        return new ResponseEntity<>(cityService.findByCityId(id),OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCity(@RequestBody City city){
        cityService.createCity(city);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCity(@PathVariable String id, @RequestBody City newCity){
        cityService.updateCity(id,newCity);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        cityService.delete(id);
        return new ResponseEntity<>(OK);
    }


    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handlerCityNotFoundException(CityNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<String> handlerCityAlreadyExistsException(CityAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }


}
