package pack;
import org.json.JSONObject;
public class CartoJSON{
    public static class Car {
        String brand;
        String model;
        int year;
        public Car(String brand, String model, int year) {
            this.brand = brand;
            this.model = model;
            this.year = year;
        }
    }
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Camry", 2022);
        JSONObject json = new JSONObject();
        json.put("brand", car.brand);
        json.put("model", car.model);
        json.put("year", car.year);
        System.out.println(json.toString(2));
    }
}