package shop;

import java.util.*;

public class Shop {
    private List<Cashier> cashiers;

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    private Warehouse warehouse;
    private double bank;

    public Shop(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    private double getBank() {
        return bank;
    }

    public List<Cashier> getCashiers() {

        return cashiers;
    }


    // initializing warehouse
    private Integer setProductCount() {
        Random random = new Random();
        return random.nextInt(0, 50);
    }

    public Map<Product, Integer> createProductMap(List<Product> products) {
        Map<Product, Integer> productMap = new HashMap<>();
        for(Product currentProduct:products) {
            Integer currentCount = setProductCount();
            productMap.put(currentProduct, currentCount);
        }
        return productMap;
    }

    public void setBank(double amount) {
        bank += amount;
    }
    // initializing a warehouse with productMap
    public void initializeWarehouse(Map<Product, Integer> productMap) {
        warehouse = new Warehouse(productMap);
    }

    // setting cashier to client according to product type
    private Cashier getCashierByProductType(ProductType productType) {
        for(Cashier cashier: cashiers) {
            if(cashier.getProductTypes().contains(productType)) {
                return cashier;
            }
        }
        return null;
    }

    // returning actual warehouse as map Product, Quantity
    public Map<Product, Integer> getActualProductMap() {
        return warehouse.getProductMap();
    }

    public List<Cashier> getAvailableCashiers(List<Product> clientProducts) {
        List<Cashier> availableCashiers = new ArrayList<>();
        for(Product product:clientProducts) {
            ProductType productType = product.getProductDescription().getProductType();
            Cashier availableCashier = getCashierByProductType(productType);
            if(availableCashier != null) {
                availableCashiers.add(availableCashier);
            }
        }
        return availableCashiers;
    }


    public Map<Product, Integer> assignProductsToCashier (Client client, Cashier cashier) {
         //checking client's basket and setting cashiers for client
         Map<Product, Integer> productsToScan = new HashMap<>();
         for (Map.Entry<Product, Integer> entry : client.getBasket().entrySet()) {
             ProductType productType = entry.getKey().getProductDescription().getProductType();
             if (cashier.equals(getCashierByProductType(productType))) {
                 productsToScan.put(entry.getKey(), entry.getValue());
             }
             return null;
         }
         return productsToScan;
    }

    public double processBuying(Map<Product, Integer> productToScan) {
        double totalPrice = 0.0;
        for(Map.Entry<Product, Integer> entry:productToScan.entrySet()) {
            totalPrice += entry.getKey().getPrice();
        }
        return totalPrice;
    }


    //    public Map.Entry<Product, Integer> getProductEntryByName(String name) {
//        Set<Map.Entry<Product, Integer>> entries = getActualWarehouse().entrySet();
//        for (Map.Entry<Product, Integer> entry: entries) {
//            if(entry.getKey().equals(name)) {
//                return entry;
//            }
//        }
//        return null;
//    }



}
