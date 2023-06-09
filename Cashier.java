package shop;

import java.util.Map;
import java.util.Set;

public class Cashier {

    private String name;
    private int ageExp; // лет опыта
    private Set<ProductType> productTypes;


    public Cashier(String name, int ageExp, Set<ProductType> productTypes) {
        this.name = name;
        this.ageExp = ageExp;
        this.productTypes = productTypes;
    }

//    public void addProductType(ProductType productType) {
//
//        productTypes.add(productType);
//    }

    public Set<ProductType> getProductTypes() {

        return productTypes;
    }

    public String getName() {

        return name;
    }

//    public int getAgeExp() {
//
//        return ageExp;
//    }

    public double scanProducts(Map<Product, Integer> clientsBasket) {
        double totalAmount = 0.0;
        for (Map.Entry<Product, Integer> entry : clientsBasket.entrySet()) {
            totalAmount += entry.getKey().getPrice();
        }
        return totalAmount;
    }

}
