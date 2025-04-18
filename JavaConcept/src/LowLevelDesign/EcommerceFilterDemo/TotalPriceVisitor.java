package LowLevelDesign.EcommerceFilterDemo;

public class TotalPriceVisitor implements ProductVisitor{
    private double total = 0;

    @Override
    public void visit(Product product) {
        total+= product.getPrice();
    }
    public double getTotal() {
        return total;
    }
}
