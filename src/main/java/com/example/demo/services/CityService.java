package com.example.demo.services;
import com.example.demo.entities.City;
import com.example.demo.exception.CityAlreadyExistsException;
import com.example.demo.exception.CityNotFoundException;
import com.example.demo.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAll(String name) {
        if (name == null){
        return cityRepository.findAll();
    }
        return cityRepository.findAllByName(name);
    }

    public void createCity(City city) {
        Optional<City> newCity = cityRepository.findByName(city.getName());
        if (newCity.isPresent()){
            throw new CityAlreadyExistsException("city already exists with name: " + city.getName());
        }
        cityRepository.save(city);
    }

    public void updateCity(String id, City newCity) {
            City oldCity = findByCityId(id);
            oldCity.setName(newCity.getName());
            cityRepository.save(oldCity);

    }

    public City findByCityId(String id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found with id: " + id));
    }



    public void delete(String id) {
        cityRepository.deleteById(id);
    }

}
