package LowLevelDesign.EcommerceFilterDemo;

import java.util.Objects;

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
    @Override
    public boolean equals(Object o){
      if(this == o){
          return true;
      }
      if(o==null || o.getClass()!=this.getClass()){
          return false;
      }
      Product p = (Product) o;
      return Double.compare(p.price,this.price) == 0
              && Objects.equals(p.name,this.name)
              && Objects.equals(p.category,this.category)
              && Objects.equals(p.brand,this.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, brand, price);
    }
    public void accept(TotalPriceVisitor visitor){
        visitor.visit(this);
    }
}
