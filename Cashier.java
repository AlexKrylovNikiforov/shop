package shop;

import java.util.HashMap;
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

    public double sellProducts(Map<Product, Integer> clientBasket) {
        double priceToPay = 0.0;
        Set<Map.Entry<Product,Integer>> productsToSell = clientBasket.entrySet();
        for (Map.Entry<Product,Integer> product: productsToSell) {
            priceToPay += product.getKey().getPrice();
            productsToSell.remove(product);
        }
        return priceToPay;
    }

    //TODO
    public double receiveReturnedProducts(Map<Product, Integer> productToReturn, Shop shop) {
        return 0.0;
    }

}
