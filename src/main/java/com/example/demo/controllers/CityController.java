package com.example.demo.controllers;

import com.example.demo.entities.City;
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

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAll() {
        return new ResponseEntity<>(cityService.getAll(), OK);
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

}
