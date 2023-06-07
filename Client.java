package shop;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private final String name;
    private double money;
    private Map<Product, Integer> basket;

public Client (String name, double money){
    this.name = name;
    this.money = money;
    basket  = new HashMap<>();
}
    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public Map<Product, Integer> getBasket() {
        return basket;
    }

    public void addProductToBasket(Product product, int count) {
            if(basket.containsKey(product)) {
                basket.put(product, basket.get(product) + count);
            } else {
                basket.put(product, count);
            }
    }

    public void removeProductFromBasket (Product product, int count) {
        int currentCount = basket.getOrDefault(product, 0);
        if(currentCount > count) {
            basket.put(product, basket.get(product) - count);
        } else {
            basket.remove(product);
        }
    }

    private void payForProducts(Map<Product, Integer> basket, double totalPrice) {
        totalPrice = 0.0;
        Set<Product> products = basket.keySet();
        for(Product product:products) {
            totalPrice += product.getPrice();
        }
    }
    
    //public boolean isEnoughMoney(double amount) {
    //    return (amount <= money);
    //}


}
