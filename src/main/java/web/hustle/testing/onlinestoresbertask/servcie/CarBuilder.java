package web.hustle.testing.onlinestoresbertask.servcie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import web.hustle.testing.onlinestoresbertask.entity.*;

/**
 * Класс для создание объектов различных типов автомобилей.
 * В зависимости от переданного типа автомобиля, создает соответствующий объект.
 * Используется в контроллере для создания автомобилей перед их сохранением.
 */
@Component
public class CarBuilder {
    private static final Logger logger = LoggerFactory.getLogger(CarBuilder.class);

    /**
     * Создает объект автомобиля в зависимости от переданного типа.
     *
     * @param carType Тип автомобиля, который нужно создать. Возможные значения:
     *                "ElectricCar", "PassengerCar", "Truck".
     *
     * @return Созданный объект автомобиля соответствующего типа
     * @throws RuntimeException Если передан некорректный тип автомобиля
     */
    public Car buildCar(String carType) {
        return switch (carType) {
            case "ElectricCar" -> createElectricCar();
            case "PassengerCar" -> createPassengerCar();
            case "Truck" -> createTruck();
            default -> {
                logger.warn("Invalid car type: {}", carType);
                throw new RuntimeException("Invalid car type: " + carType);
            }
        };
    }

    /**
     * Создает новый объект типа **PassengerCar**.
     *
     * @return Новый экземпляр **PassengerCar**
     */
    private PassengerCar createPassengerCar() {
        return new PassengerCar();
    }

    /**
     * Создает новый объект типа **Truck**.
     *
     * @return Новый экземпляр **Truck**
     */
    private Truck createTruck() {
        return new Truck();
    }

    /**
     * Создает новый объект типа **ElectricCar**.
     *
     * @return Новый экземпляр **ElectricCar**
     */
    private ElectricCar createElectricCar() {
        return new ElectricCar();
    }
}
