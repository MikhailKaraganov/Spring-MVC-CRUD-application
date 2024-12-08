package web.hustle.testing.onlinestoresbertask.controller;

import org.aspectj.util.GenericSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.hustle.testing.onlinestoresbertask.entity.Car;
import web.hustle.testing.onlinestoresbertask.entity.ElectricCar;
import web.hustle.testing.onlinestoresbertask.entity.PassengerCar;
import web.hustle.testing.onlinestoresbertask.entity.Truck;
import web.hustle.testing.onlinestoresbertask.servcie.CarBuilder;
import web.hustle.testing.onlinestoresbertask.servcie.CarService;


/**
 * Контроллер для выполнения CRUD операций над автомобилями.
 * Он взаимодействует с сервисом **CarService** для выполнения операций с базой данных и **CarBuilder** для
 * создания автомобилей.
 */
@Controller
@RequestMapping("/")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;
    private final CarBuilder carBuilder;

    /**
     * Конструктор контроллера.
     * Внедряет зависимости **CarService** и **CarBuilder**.
     *
     * @param carService Сервис для работы с автомобилями
     * @param carBuilder Строитель для создания автомобилей
     */
    @Autowired
    public CarController(CarService carService, CarBuilder carBuilder) {
        this.carService = carService;
        this.carBuilder = carBuilder;
    }

    /**
     * Получить список всех автомобилей.
     * Обрабатывает GET-запрос на корневой путь ("/").
     * Заполняет модель атрибутом "cars" со списком всех автомобилей и возвращает представление "allCarList".
     *
     * @param model Модель, которая будет передана в представление
     * @return Имя представления для отображения списка автомобилей
     */
    @GetMapping
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.findAllCars());
        return "allCarList";
    }

    /**
     * Получить автомобиль по его ID.
     * Обрабатывает GET-запрос на путь "/{id}" ({id} — это идентификатор автомобиля),
     * Заполняет модель атрибутами "car" и "carType" для отображения информации об автомобиле.
     *
     * @param id Идентификатор автомобиля
     * @param model Модель, которая будет передана в представление
     * @return Имя представления для отображения информации об автомобиле
     */
    @GetMapping("/{id}")
    public String getCarById(@PathVariable Long id, Model model) {
        Car car = carService.findCarById(id);
        String carType = car.getClass().getSimpleName();
        model.addAttribute("car", car);
        model.addAttribute("carType", carType);
        return "carInfo";
    }

    /**
     * Показать форму для создания нового автомобиля.
     * Обрабатывает GET-запрос на путь "/new" и создает новый автомобиль на основе типа,
     * переданного через параметр "carType".
     * После создания автомобиля передает данные на форму для редактирования или создания автомобиля.
     *
     * @param carType Тип создаваемого автомобиля (например, "PassengerCar", "Truck", "ElectricCar")
     * @param model Модель, которая будет передана в представление
     * @return Имя представления для отображения формы создания автомобиля
     */
    @GetMapping("/new")
    public String createCarForm(@RequestParam String carType, Model model) {
        logger.info("Creating new car with type: " + carType);
        Car car = carBuilder.buildCar(carType);
        carService.saveCar(car);
        logger.info("New car with id {} created", car.getId());
        model.addAttribute("car", car);
        model.addAttribute("carType", carType);
        return "createEditCar";  // Переход на форму для создания автомобиля
    }

    /**
     * Показать форму для редактирования автомобиля.
     * Обрабатывает GET-запрос на путь "/{id}/edit", где {id} — это идентификатор автомобиля.
     * Заполняет модель атрибутами "car" и "carType" для отображения формы редактирования автомобиля.
     *
     * @param id Идентификатор автомобиля
     * @param model Модель, которая будет передана в представление
     * @return Имя представления для отображения формы редактирования автомобиля
     */
    @GetMapping("/{id}/edit")
    public String editCarForm(@PathVariable Long id, Model model) {
        Car car = carService.findCarById(id);
        model.addAttribute("car", car);
        model.addAttribute("carType", car.getClass().getSimpleName());
        return "createEditCar";
    }


    /**
     * Обновить данные пассажирского автомобиля.
     * Обрабатывает POST-запрос на путь "/{id}/edit/PassengerCar" и сохраняет изменения для автомобиля
     * типа **PassengerCar**.
     *
     * @param id Идентификатор автомобиля, который нужно обновить
     * @param car Обновленный объект автомобиля типа **PassengerCar**
     * @return Перенаправление на главную страницу
     */
    @PostMapping("/{id}/edit/PassengerCar")
    public String updateCar(@PathVariable Long id,  @ModelAttribute PassengerCar car) {
        System.out.println("carID: "+car.toString());
        car.setId(id);
        carService.saveCar(car);
        return "redirect:/";
    }

    /**
     * Обновить данные грузового автомобиля.
     * Обрабатывает POST-запрос на путь "/{id}/edit/Truck" и сохраняет изменения для автомобиля
     * типа **Truck**.
     *
     * @param id Идентификатор автомобиля, который нужно обновить
     * @param car Обновленный объект автомобиля типа **Truck**
     * @return Перенаправление на главную страницу
     */
    @PostMapping("/{id}/edit/Truck")
    public String updateCar(@PathVariable Long id,  @ModelAttribute Truck car) {
        System.out.println("carID: "+car.toString());
        car.setId(id);
        carService.saveCar(car);
        return "redirect:/";
    }

    /**
     * Обновить данные электрического автомобиля.
     * Обрабатывает POST-запрос на путь "/{id}/edit/ElectricCar" и сохраняет изменения для автомобиля
     * типа **ElectricCar**.
     *
     * @param id Идентификатор автомобиля, который нужно обновить
     * @param car Обновленный объект автомобиля типа **ElectricCar**
     * @return Перенаправление на главную страницу
     */
    @PostMapping("/{id}/edit/ElectricCar")
    public String updateCar(@PathVariable Long id, @ModelAttribute ElectricCar car) {
        System.out.println("carID: "+car.toString());
        car.setId(id);
        carService.saveCar(car);
        return "redirect:/";
    }

    /**
     * Удалить автомобиль.
     * Обрабатывает GET-запрос на путь "/{id}/delete" и удаляет автомобиль с указанным идентификатором.
     *
     * @param id Идентификатор автомобиля, который нужно удалить
     * @return Перенаправление на главную страницу
     */
    @GetMapping("/{id}/delete")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/";
    }
}