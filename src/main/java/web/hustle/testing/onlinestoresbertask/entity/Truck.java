package web.hustle.testing.onlinestoresbertask.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Truck наследуется от **Car** и расширяется полями payloadCapacity и cargoType.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("Truck")
@NoArgsConstructor
@AllArgsConstructor
public class Truck extends Car {
    /** Грузоподъемность */
    private double payloadCapacity;
    /** Тип кузова */
    private String cargoType;

    /**
     * Конструктор класса **Truck**
     * вызывает конструктор класса родителя и далее присваивает значения уникальным для класса полям
     * @param model
     * @param brand
     * @param price
     * @param color
     * @param yearOfManufacture
     * @param payloadCapacity
     * @param cargoType
     */
    public Truck(String model, String brand, double price, String color, int yearOfManufacture, double payloadCapacity, String cargoType) {
        super(model, brand, price, color, yearOfManufacture);
        this.payloadCapacity = payloadCapacity;
        this.cargoType = cargoType;
    }

}
