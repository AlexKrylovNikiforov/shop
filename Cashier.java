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

    public double sellProducts(Client client) {
        Set<Map.Entry<Product, Integer>> productsToPay = client.getBasket().entrySet();
        double totalAmount = 0.0;
        for (Map.Entry<Product, Integer> entry : productsToPay) {
            totalAmount += entry.getKey().getPrice();
        }
        return totalAmount;
    }

    public double receiveFromBuyer(Client client) {
        return sellProducts(client);
    }

    public void requestProductFromShop (Client client, Shop shop) {
        Set<Map.Entry<Product, Integer>> productsToRequest = client.getBasket().entrySet();
        for(Map.Entry<Product, Integer> entry:productsToRequest) {
            shop.transferProductsToCashier(entry);
        }
    }

    public void returnDeclinedProducts(Map<Product, Integer> clientsBasket, Shop shop) {
        Set<Map.Entry<Product, Integer>> productsToReturn = clientsBasket.entrySet();
        for(Map.Entry<Product, Integer> entry:productsToReturn) {
            shop.returnProductsFromCashier(entry);
        }
    }

}
