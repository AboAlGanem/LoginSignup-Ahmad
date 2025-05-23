package com.example.loginsignup_ahmad.Data;

public class Car {
    private String NameCar;
    private String horse_power;
    private String owners;
    private String phone;
    private String car_num;
    private String manufacturer;
    private String car_model;
    private String test;
    private String kilometre;
    private String engine_capacity;
    private String gear_shifting_model;
    private String price;
    private String imageUrl;

    //  Constructor فارغ – مطلوب لـ Firebase
    public Car() {
    }

    //  Constructor كامل – يُستخدم عند إنشاء سيارة جديدة
    public Car(String NameCar, String horse_power, String owners, String phone,
               String car_num, String manufacturer, String car_model, String test,
               String kilometre, String engine_capacity, String gear_shifting_model,
               String price, String imageUrl) {
        this.NameCar = NameCar;
        this.horse_power = horse_power;
        this.owners = owners;
        this.phone = phone;
        this.car_num = car_num;
        this.manufacturer = manufacturer;
        this.car_model = car_model;
        this.test = test;
        this.kilometre = kilometre;
        this.engine_capacity = engine_capacity;
        this.gear_shifting_model = gear_shifting_model;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    //  Getters – ضرورية لتحويل البيانات من Firebase
    public String getNameCar() {
        return NameCar;
    }

    public String getHorse_power() {
        return horse_power;
    }

    public String getOwners() {
        return owners;
    }

    public String getPhone() {
        return phone;
    }

    public String getCar_num() {
        return car_num;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCar_model() {
        return car_model;
    }

    public String getTest() {
        return test;
    }

    public String getKilometre() {
        return kilometre;
    }

    public String getEngine_capacity() {
        return engine_capacity;
    }

    public String getGear_shifting_model() {
        return gear_shifting_model;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}