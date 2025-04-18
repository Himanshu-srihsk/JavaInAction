package LowLevelDesign.EcommerceFilterDemo;

import java.util.List;

public class AndExpression implements Expression{
    Expression expression1;
    Expression expression2;

    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public List<Product> interpret(List<Product> products) {
        List<Product> filtered1 = expression1.interpret(products);
        return expression2.interpret(filtered1);
    }
}
