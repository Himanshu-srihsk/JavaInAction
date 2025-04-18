package LowLevelDesign.EcommerceFilterDemo;

import java.util.List;
import java.util.stream.Collectors;

public class PriceExpression implements Expression{
    private double price;
    private boolean isLessThan;

    PriceExpression(double price,boolean isLessThan){
        this.price = price;
        this.isLessThan = isLessThan;
    }
    @Override
    public List<Product> interpret(List<Product> products) {
        return products.stream().filter(p -> isLessThan? p.getPrice() < price: p.getPrice() > price).collect(Collectors.toList());
    }
}
