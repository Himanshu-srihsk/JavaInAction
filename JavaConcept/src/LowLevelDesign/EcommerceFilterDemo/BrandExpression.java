package LowLevelDesign.EcommerceFilterDemo;

import java.util.List;
import java.util.stream.Collectors;

public class BrandExpression implements Expression{
    private String brand;

    public BrandExpression(String brand) {
        this.brand = brand.toLowerCase();
    }

    @Override
    public List<Product> interpret(List<Product> products) {
        return products.stream().filter(p-> p.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
    }
}
