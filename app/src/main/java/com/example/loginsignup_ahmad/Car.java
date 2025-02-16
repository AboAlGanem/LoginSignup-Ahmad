package com.example.loginsignup_ahmad;

public class Car {
    private String name;
    private String description;
    private String address;
    private String phone;
    private  String horse_power;
    private  String owners;
    private String color;
    private  String car_num;
    private  String manufacturer;
    private  String year;
    private  String Car_model;
    private  String test;
    private  String kilometre;
    private  String Engine_capacity;
    private  String Gear_shifting_model;
    private  String   price;
    private String photo;

    public Car(String name, String description, String address, String phoneString, String horse_power, String owners, String phone, String color,
               String car_num, String manufacturer, String year, String car_model, String test,
               String kilometre, String engine_capacity, String gear_shifting_model, String price, String photo){
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.horse_power = horse_power;
        this.owners = owners;
        this.phone = phone;
        this.color = color;
        this.car_num = car_num;
        this.manufacturer = manufacturer;
        this.year = year;
        Car_model = car_model;
        this.test = test;
        this.kilometre = kilometre;
        Engine_capacity = engine_capacity;
        Gear_shifting_model = gear_shifting_model;
        this.price = price;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getHorse_power() {
    return horse_power;
}

    public void setHorse_power(String horse_power) {
    this.horse_power = horse_power;
}

    public String getOwners() {
    return owners;
}

    public void setOwners(String owners) {
    this.owners = owners;
}

    public String getPhone() {
    return phone;
}

    public void setPhone(String phone) {
    this.phone = phone;
}

    public String getColor() {
    return color;
}

    public void setColor(String color) {
    this.color = color;
}

    public String getCar_num() {
    return car_num;
}

    public void setCar_num(String car_num) {
    this.car_num = car_num;
}

    public String getManufacturer() {
    return manufacturer;
}

    public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
}

    public String getYear() {
    return year;
}

    public void setYear(String year) {
    this.year = year;
}

    public String getCar_model() {
    return Car_model;
}

    public void setCar_model(String car_model) {
    Car_model = car_model;
}

    public String getTest() {
    return test;
}

    public void setTest(String test) {
    this.test = test;
}

    public String getKilometre() {
    return kilometre;
}

    public void setKilometre(String kilometre) {
    this.kilometre = kilometre;
}

    public String getEngine_capacity() {
    return Engine_capacity;
}

    public void setEngine_capacity(String engine_capacity) {
    Engine_capacity = engine_capacity;
}

    public String getGear_shifting_model() {
    return Gear_shifting_model;
}

    public void setGear_shifting_model(String gear_shifting_model) {
    Gear_shifting_model = gear_shifting_model;
}

    public String getPrice() {
    return price;
}

    public void setPrice(String price) {
    this.price = price;
}

    public String getPhoto() {
    return photo;
}

    public void setPhoto(String photo) {
    this.photo = photo;
}

    @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", address='" + address + '\'' +
                    ", phone='" + phone + '\'' +
                    ", horse_power='" + horse_power + '\'' +
                    ", owners='" + owners + '\'' +
                    ", color='" + color + '\'' +
                    ", car_num='" + car_num + '\'' +
                    ", manufacturer='" + manufacturer + '\'' +
                    ", year='" + year + '\'' +
                    ", Car_model='" + Car_model + '\'' +
                    ", test='" + test + '\'' +
                    ", kilometre='" + kilometre + '\'' +
                    ", Engine_capacity='" + Engine_capacity + '\'' +
                    ", Gear_shifting_model='" + Gear_shifting_model + '\'' +
                    ", price='" + price + '\'' +
                    ", photo='" + photo + '\'' +
                    '}';
        }
}


