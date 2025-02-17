package com.example.loginsignup_ahmad;

public class Car {
    private String name;
    private String description;
    private String address;
    private String phone;
    private  String   price;
    private String photo;

    public Car(String name, String description, String address, String phone,String price, String photo){
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.phone = phone;
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

    public String getPhone() {
    return phone;
}

    public void setPhone(String phone) {
    this.phone = phone;
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
                    ", price='" + price + '\'' +
                    ", photo='" + photo + '\'' +
                    '}';
        }
}


