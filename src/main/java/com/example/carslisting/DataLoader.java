package com.example.carslisting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CarRepository carRepository;

    @Override
    public void run(String... strings) throws Exception {

        //create "category"
        Category category = new Category();
        category.setName("Truck");

        //create "car"
        Car car = new Car();
        car.setName("Ford F-150");
        car.setYear("2020");
        car.setPrice("$35,499");

        //create empty set "cars", add "car", and add the set to "category"
        Set<Car> cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        //define and save category for "car"
        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////

        category = new Category();
        category.setName("SUV");

        Car car1 = new Car();
        car1.setName("Land Rover Discovery");
        car1.setYear("2018");
        car1.setPrice("58,399");

        Car car2 = new Car();
        car2.setName("Porsche Cayenne Turbo S");
        car2.setYear("2019");
        car2.setPrice("$94,899");

        Car car3 = new Car();
        car3.setName("Mercedes-Benz GLS63 AMG");
        car3.setYear("2019");
        car3.setPrice("$78,299");

        cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        category.setCars(cars);
        categoryRepository.save(category);

        car1.setCategory(category);
        car2.setCategory(category);
        car3.setCategory(category);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

//////////////////////////////////////////////////////////////////////


        category = new Category();
        category.setName("Commercial Fleet");
        car = new Car();
        car.setName("Chevrolet Silverado Z71");
        car.setYear("2019");
        car.setPrice("$47,899");

        cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////

        category = new Category();
        category.setName("Car");
        car = new Car();
        car.setName("BMW 335i");
        car.setYear("2020");
        car.setPrice("$37,999");

        cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////

        category = new Category();
        category.setName("Motorcycle");
        car = new Car();
        car.setName("Husqvarna 901 Adventure");
        car.setYear("2022");
        car.setPrice("$18,699");

        cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////


    }

}