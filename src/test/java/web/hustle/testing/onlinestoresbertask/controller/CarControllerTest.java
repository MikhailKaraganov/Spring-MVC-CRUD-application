package web.hustle.testing.onlinestoresbertask.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import web.hustle.testing.onlinestoresbertask.entity.Car;
import web.hustle.testing.onlinestoresbertask.entity.PassengerCar;
import web.hustle.testing.onlinestoresbertask.servcie.CarBuilder;
import web.hustle.testing.onlinestoresbertask.servcie.CarService;

import java.util.List;

public class CarControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @Mock
    private CarBuilder carBuilder;

    @InjectMocks
    private CarController carController;

    private PassengerCar car;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        car = new PassengerCar("Model S", "Tesla", 50000.0, "Red", 2021, 4, 5);
        car.setId(1L);
    }

    @Test
    void testGetAllCars() throws Exception {
        when(carService.findAllCars()).thenReturn(List.of(car));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cars"))
                .andExpect(view().name("allCarList"));
    }

    @Test
    void testGetCarById() throws Exception {
        when(carService.findCarById(1L)).thenReturn(car);

        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("car"))
                .andExpect(model().attributeExists("carType"))
                .andExpect(view().name("carInfo"));
    }

    @Test
    void testCreateCarForm() throws Exception {
        when(carBuilder.buildCar("PassengerCar")).thenReturn(car);

        mockMvc.perform(get("/new?carType=PassengerCar"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("car"))
                .andExpect(model().attributeExists("carType"))
                .andExpect(view().name("createEditCar"));
    }


    @Test
    void testUpdateCar() throws Exception {
        when(carService.saveCar(any(PassengerCar.class))).thenReturn(car);

        mockMvc.perform(post("/1/edit/PassengerCar")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(car)))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));
    }

    @Test
    void testDeleteCar() throws Exception {
        doNothing().when(carService).deleteCar(1L);

        mockMvc.perform(get("/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        verify(carService, times(1)).deleteCar(1L);
    }
}

