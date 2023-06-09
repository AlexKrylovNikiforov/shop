package shop;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private Map<Product, Integer> productMap;

    public Warehouse(Map<Product, Integer> productMap) {
        this.productMap = productMap;
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public void addProduct(Product product, Integer count) {
        if(productMap.containsKey(product)) {
            productMap.put(product, productMap.get(product) + count);
        } else {
            productMap.put(product, count);
        }
    }
    public void removeProduct(Product product, Integer count) {
      if (productMap.containsKey(product) && productMap.get(product) >= count) {
           Integer currentCount = productMap.get(product);
           productMap.put(product, currentCount - count);
       }
    }
}
