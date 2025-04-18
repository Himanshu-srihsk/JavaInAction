package LowLevelDesign.EcommerceFilterDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrExpression implements Expression{
    Expression expression1;
    Expression expression2;

    public OrExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public List<Product> interpret(List<Product> products) {
        List<Product> filtered1 = expression1.interpret(products);
        List<Product> filtered2 = expression2.interpret(products);
        Set<Product> resultSet = new HashSet<>(filtered1);
        resultSet.addAll(filtered2);
        return new ArrayList<>(resultSet);
    }
}
