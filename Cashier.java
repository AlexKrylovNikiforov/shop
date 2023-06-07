package shop;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cashier {

    private String name;
    private int ageExp; // лет опыта
    private final Set<ProductType> productTypes = new HashSet<>();


    public Cashier(String name, int ageExp) {
        this.name = name;
        this.ageExp = ageExp;
    }

    public void addProductType(ProductType productType) {

        productTypes.add(productType);
    }

    public Set<ProductType> getProductTypes() {

        return productTypes;
    }

    public String getName() {

        return name;
    }

    public int getAgeExp() {

        return ageExp;
    }

    public double sellProducts(Product product, int count, Shop shop) {
        double moneyToPay = product.getPrice();
        return moneyToPay;
    }

    public double receiveReturnedProducts(Map<Product, Integer> productToReturn, Shop shop) {

    }


}
