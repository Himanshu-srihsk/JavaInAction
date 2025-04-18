package LowLevelDesign.EcommerceFilterDemo;

import java.util.Arrays;
import java.util.List;
/*
Design an extensible filtering system for an e-commerce platform that allows dynamic filtering of products
based on attributes like category, brand, and price using the Interpreter design pattern.
 Additionally, calculate the total price of the filtered products using the Visitor pattern.
The system should support flexible combinations of filter conditions (AND, OR) and be open for future enhancements like discount logic,
 new filters, or operations.
 */
public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Apple", "Smartphones", "iPhone 13", 999),
                new Product("Apple", "Computers", "MacBook Air", 1200),
                new Product("Samsung", "Electronics", "Samsung TV", 700),
                new Product("LG", "Electronics", "Washing Machine", 400)

        );

        //Create filter: (category = "Electronics" OR brand = "Apple") AND price > 500

        Expression categoryElectronics = new CategoryExpression("Electronics");
        Expression brandApple = new BrandExpression("Apple");
        Expression priceAbove500 = new PriceExpression(500, false);

        Expression orCondition = new OrExpression(categoryElectronics, brandApple);
        Expression finalCondition = new AndExpression(orCondition, priceAbove500);

        List<Product> filtered = finalCondition.interpret(products);

        System.out.println("Filtered Products:");
        for (Product p : filtered) {
            System.out.println(p.getBrand() + " " + p.getCategory() + " $" + p.getPrice());
        }
        TotalPriceVisitor visitor = new TotalPriceVisitor();
        filtered.forEach(p -> p.accept(visitor));
        double moneyToPay = visitor.getTotal();
        System.out.println("\n Total Price of Filtered Products: $" + visitor.getTotal());
    }
}
