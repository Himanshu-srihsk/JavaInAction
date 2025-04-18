package LowLevelDesign.EcommerceFilterDemo;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryExpression implements Expression{
    private String category;

    public CategoryExpression(String category) {
        this.category = category.toLowerCase();
    }

    @Override
    public List<Product> interpret(List<Product> products) {
        return products.stream().filter(p-> p.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }
}
