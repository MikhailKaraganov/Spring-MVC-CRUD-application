package web.hustle.testing.onlinestoresbertask.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ElectricCar наследуется от **Car** и расширяется полями batteryCapacity и rangePerCharge.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ElectricCar")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ElectricCar extends Car {
    /** Емкость батареи в кВ\ч  */
    private double batteryCapacity;
    /** Расстояние в км на одном заряде*/
    private double rangePerCharge;

    /**
     * Конструктор класса **ElectricCar**
     * вызывает конструктор класса родителя и далее присваивает значения уникальным для класса полям
     * @param model
     * @param brand
     * @param price
     * @param color
     * @param yearOfManufacture
     * @param batteryCapacity
     * @param rangePerCharge
     */
    public ElectricCar(String model, String brand, double price, String color, int yearOfManufacture, double batteryCapacity, double rangePerCharge) {
        super(model, brand, price, color, yearOfManufacture);
        this.batteryCapacity = batteryCapacity;
        this.rangePerCharge = rangePerCharge;
    }
}
