package LowLevelDesign.EcommerceFilterDemo;

public class Product {
    private String name;
    private String category;
    private String brand;
    private double price;

    public Product(String brand, String category, String name, double price) {
        this.brand = brand;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
