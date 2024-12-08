package web.hustle.testing.onlinestoresbertask.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import web.hustle.testing.onlinestoresbertask.entity.Car;
import web.hustle.testing.onlinestoresbertask.entity.ElectricCar;
import web.hustle.testing.onlinestoresbertask.repository.CarRepository;
import web.hustle.testing.onlinestoresbertask.servcie.CarService;

import java.util.Optional;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private ElectricCar car;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        car = new ElectricCar("Model S", "Tesla", 50000.0, "Red", 2021, 34, 600);
        car.setId(1L);
    }

    @Test
    void testSaveCar() {
        when(carRepository.save(any(ElectricCar.class))).thenReturn(car);

        Car savedCar = carService.saveCar(car);

        assertNotNull(savedCar);
        assertEquals("Model S", savedCar.getModel());
        verify(carRepository, times(1)).save(any(Car.class));  // Проверка, что метод save был вызван 1 раз
    }

    @Test
    void testFindCarById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        Car foundCar = carService.findCarById(1L);

        assertNotNull(foundCar);
        assertEquals(1L, foundCar.getId());
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteCar() {
        doNothing().when(carRepository).deleteById(1L);

        carService.deleteCar(1L);

        verify(carRepository, times(1)).deleteById(1L);
    }
}

