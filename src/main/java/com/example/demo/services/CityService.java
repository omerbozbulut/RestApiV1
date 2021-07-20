package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public void createCity(City city) {
        cityRepository.save(city);
    }

    public void updateCity(String id, City newCity) {
            City oldCity = findByCityId(id);
            oldCity.setName(newCity.getName());
            cityRepository.save(oldCity);

    }

    public City findByCityId(String id) {
        System.out.println("denemeee " + cityRepository.getById(id).getName());
        return cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
    }

    public void delete(String id) {
        cityRepository.deleteById(id);
    }
}
