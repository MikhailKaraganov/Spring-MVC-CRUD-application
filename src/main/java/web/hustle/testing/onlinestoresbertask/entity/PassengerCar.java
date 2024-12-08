package web.hustle.testing.onlinestoresbertask.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * PassengerCar наследуется от **Car** и расширяется полями doors и seatingCapacity.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("PassengerCar")
@NoArgsConstructor
public class PassengerCar extends Car {
    /** Количестов дверей  */
    private int doors;
    /** Количество мест */
    private int seatingCapacity;

    /**
     * Конструктор класса **PassengerCar**
     *  вызывает конструктор класса родителя и далее присваивает значения уникальным для класса полям
     * @param model
     * @param brand
     * @param price
     * @param color
     * @param yearOfManufacture
     * @param doors
     * @param seatingCapacity
     */
    public PassengerCar(String model, String brand, double price, String color, int yearOfManufacture, int doors, int seatingCapacity) {
        super(model, brand, price, color, yearOfManufacture);
        this.doors = doors;
        this.seatingCapacity = seatingCapacity;
    }

}
