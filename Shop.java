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

    public void setBank(double amount) {
        bank += amount;
    }

    public Map<Product, Integer> createProductMap(List<Product> products) {
        Map<Product, Integer> productMap = new HashMap<>();
        for(Product currentProduct:products) {
            Integer currentCount = setProductCount();
            productMap.put(currentProduct, currentCount);
        }
        return productMap;
    }

    // initializing a warehouse with productMap
    public void initializeWarehouse(Map<Product, Integer> productMap) {
        warehouse = new Warehouse(productMap);
    }

    // returning actual warehouse as map Product, Quantity

    public Map<Product, Integer> getActualProductMap() {
        return warehouse.getProductMap();
    }

    private void transferProductsToClient(Map<Product, Integer> clientBasket) {
        for(Map.Entry<Product, Integer> entry:clientBasket.entrySet()) {
            warehouse.removeProduct(entry.getKey(), entry.getValue());
        }
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

//    public List<Cashier> getAvailableCashiers(List<Product> clientProducts) {
//        List<Cashier> availableCashiers = new ArrayList<>();
//        for(Product product:clientProducts) {
//            ProductType productType = product.getProductDescription().getProductType();
//            Cashier availableCashier = getCashierByProductType(productType);
//            if(availableCashier != null) {
//                availableCashiers.add(availableCashier);
//            }
//        }
//        return availableCashiers;
//    }

    public Set<Cashier> assignCashiers (Client client) {
        Set<Cashier> assignedCashiers = new HashSet<>();
        for(Map.Entry<Product, Integer> entry:client.getBasket().entrySet()) {
            Product currentProduct = entry.getKey();
            Cashier cashier = getCashierByProductType(currentProduct.getProductDescription().getProductType());
            if(cashier != null) {
                assignedCashiers.add(cashier);
            }
            return assignedCashiers;
        }
        return null;
    }
    public double performSale(Set<Product> products, Set<Cashier> cashiers) {
         //checking client's basket and setting cashiers for client
        double totalPrice = 0.0;
        for(Cashier cashier:cashiers) {
             for(Product product:products) {
                 if(cashier.getProductTypes().contains(product.getProductDescription().getProductType())) {
                     totalPrice += cashier.scanProduct(product);
                 }
             }
         }
        return totalPrice;
    }

    public void processPayment (Client client, double totalPrice) {
        client.payForProducts(totalPrice);
        bank += totalPrice;
        transferProductsToClient(client.getBasket());
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
