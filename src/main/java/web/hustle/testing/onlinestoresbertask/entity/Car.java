package web.hustle.testing.onlinestoresbertask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Абстрактный класс Car представляет собой сущность автомобиля с основными общими полями.
 * Методы (помимо генерируемых Lombok) не добавлял т.к. в рамках проекта не предусмотрено какого либо поведения сущностей.
 * <p>
 * Аннотации:
 * - **@Inheritance(strategy = InheritanceType.SINGLE_TABLE)** указыввает,
 * что все наследники данного класса будут храниться в одной таблице.
 * - **@DiscriminatorColumn(name = "car_type", discriminatorType = DiscriminatorType.STRING)** добавляет столбец
 * **car_type**, который будет содержать тип автомобиля для каждой строки в таблице, что
 * позволит отличать разные типы автомобилей.
 * <p>
 *  Является родителем для **ElectricCar**, **PassengerCar** и **Truck**.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public abstract class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private String brand;
    private double price;
    private String color;
    private int yearOfManufacture;

    /**
     * Конструктор для создания автомобиля.
     *
     * @param model Модель автомобиля
     * @param brand Бренд автомобиля
     * @param price Цена автомобиля
     * @param color Цвет автомобиля
     * @param yearOfManufacture Год выпуска автомобиля
     */
    public Car(String model, String brand, double price, String color, int yearOfManufacture) {
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
    }

}

