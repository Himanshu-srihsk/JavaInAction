package LowLevelDesign.EcommerceFilterDemo;

import java.util.List;

public interface Expression {
    List<Product> interpret(List<Product> products);
}
