package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Shop {
    private List<Cashier> cashiers = new ArrayList<>();
    private Warehouse warehouse = new Warehouse();
    private double bank;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public double getBank() {
        return bank;
    }
    public List<Cashier> getCashiers() {

        return cashiers;
    }

    public Cashier getCashierByProductType(ProductType productType) {
        for(Cashier cashier: cashiers) {
            if(cashier.getProductTypes().contains(productType)) {
                return cashier;
            }
        }
        return null;
    }
    public Map<Product, Integer> getActualWarehouse() {
        return warehouse.getProductMap();
    }

    public Integer getProductCountByName(String name) {
        Set<Map.Entry<Product, Integer>> entries = getActualWarehouse().entrySet();
        for (Map.Entry<Product, Integer> entry : entries) {
            if(entry.getKey().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Map.Entry<Product, Integer> getProductEntryByName(String name) {
        Set<Map.Entry<Product, Integer>> entries = getActualWarehouse().entrySet();
        for (Map.Entry<Product, Integer> entry: entries) {
            if(entry.getKey().equals(name)) {
                return entry;
            }
        }
        return null;
    }

}
