package web.hustle.testing.onlinestoresbertask.servcie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.hustle.testing.onlinestoresbertask.OnlineStoreSberTaskApplication;
import web.hustle.testing.onlinestoresbertask.entity.Car;
import web.hustle.testing.onlinestoresbertask.repository.CarRepository;

import java.util.List;

/**
 * Сервис для выполнения операций с автомобилями.
 * Содержит методы для получения, сохранения и удаления автомобилей.
 * Использует **CarRepository** для взаимодействия с базой данных.
 */
@Service
public class CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;

    /**
     * Конструктор для внедрения зависимости **CarRepository**.
     * @param carRepository Репозиторий для работы с автомобилями
     */
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Получить все автомобили из базы данных.
     * Использует **CarRepository** для поиска всех автомобилей в базе данных.
     *
     * @return Список всех автомобилей в базе данных
     */
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    /**
     * Получить автомобиль по его идентификатору.
     * Использует **CarRepository** для поиска автомобиля по ID.
     * Если автомобиль с данным ID не найден, метод возвращает **null**.
     *
     * @param id Идентификатор автомобиля
     * @return Объект автомобиля с указанным ID, или **null**, если автомобиль не найден
     */
    public Car findCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    /**
     * Сохранить или обновить информацию о автомобиле.
     * Сохраняет автомобиль в базе данных, используя **CarRepository**.
     * Если автомобиль уже существует, то его данные будут обновлены.
     *
     * @param car Объект автомобиля, который нужно сохранить
     * @return Сохраненный объект автомобиля
     */
    public Car saveCar(Car car) {
        logger.info("Saving car: {}", car);
        return carRepository.save(car);
    }
    
    /**
     * Удалить автомобиль по его идентификатору.
     * Использует **CarRepository** для удаления автомобиля из базы данных по ID.
     *
     * @param id Идентификатор автомобиля, который нужно удалить
     */
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
        logger.info("Car with id: {} deleted", id);
    }

}
