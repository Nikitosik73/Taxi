package com.mirea.taxiapp.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "carNote")
public class CarNote {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titleCar;
    private String saleCar;
    private String speedCar;
    private int priority;

    public CarNote(int id, String titleCar, String saleCar, String speedCar, int priority) {
        this.id = id;
        this.titleCar = titleCar;
        this.saleCar = saleCar;
        this.speedCar = speedCar;
        this.priority = priority;
    }

    @Ignore
    public CarNote(String titleCar, String saleCar, String speedCar, int priority){
        this(0, titleCar, saleCar, speedCar, priority);
    }

    public int getId() {
        return id;
    }

    public String getTitleCar() {
        return titleCar;
    }

    public String getSaleCar() {
        return saleCar;
    }

    public String getSpeedCar() {
        return speedCar;
    }

    public int getPriority() {
        return priority;
    }
}