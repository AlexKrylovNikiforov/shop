package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Shop {
    private List<Cashier> cashiers = new ArrayList<>();
    private Warehouse warehouse;
    private double bank;

    private void setBank(double bank) {
        this.bank = bank;
    }

    public Shop(List<Cashier> cashiers, Warehouse warehouse, double bank) {
        this.cashiers = cashiers;
        this.warehouse = warehouse;
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

    // setting cashier to client according to product type
    public Cashier getCashierByProductType(ProductType productType) {
        for(Cashier cashier: cashiers) {
            if(cashier.getProductTypes().contains(productType)) {
                return cashier;
            }
        }
        return null;
    }
    // initializing a warehouse with productMap
    private void initializeWarehouse(Map<Product, Integer> productMap) {
        Warehouse warehouse = new Warehouse(productMap);
    }

    // returning actual warehouse as map Product, Quantity
    public Map<Product, Integer> getActualProductMap() {
        return warehouse.getProductMap();
    }

     public void processSale(Client client, List<Cashier> cashiers) {
        //checking client's basket and setting cashiers for client
         Map<Product, Integer> clientBasket = client.getBasket();
         for(Map.Entry<Product, Integer> entry : clientBasket.entrySet()) {
             ProductType productType = entry.getKey().getProductDescription().getProductType();
             Cashier cashier = getCashierByProductType(productType);
             // прописать логику: забрать getProductTypes у кассира, пройти по списку товаров, для каждого кассира
             //создать отдельную корзину с говарами его категорий, передать кассиру для сканирования и подсчета
         }

         // scanning products кассир сканирует свою корзину, выводит сумму покупки
         // магазин вызывает isEnoughMoneyToPay у покупателя, если да, переходим к процессингу, если нет - товары просто удаляются из корзины

        // processing payment
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
