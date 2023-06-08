package shop;

import java.util.*;

public class Shopping {
    private List<Client> clientList = new ArrayList<>();
    private Shop shop = new Shop();

    public static void main(String[] args) {

    }
    private void initialize() {
        //creating clients
//        clientList.add(new Client("John", 150.00));
//        clientList.add(new Client("Sammie", 50.00));
//        clientList.add(new Client("Edward", 65.00));

        List<Cashier> cashiers = shop.getCashiers();
        Cashier carlos = new Cashier("Carlos", 12);
        Cashier johanna = new Cashier("Johanna", 7);
        Set<ProductType> productTypesCarlos = carlos.getProductTypes();
        Set<ProductType> productTypesJohanna = johanna.getProductTypes();
        Collections.addAll(productTypesCarlos, ProductType.MEAT, ProductType.VEGETABLE, ProductType.FISH);
        Collections.addAll(productTypesJohanna, ProductType.ALCOHOL, ProductType.TOBACCO, ProductType.FRUIT);
        Collections.addAll(cashiers, carlos, johanna);

        //creating products
        ProductDescription meat = new ProductDescription(ProductType.MEAT);
        ProductDescription vegetable = new ProductDescription(ProductType.VEGETABLE);
        ProductDescription fish = new ProductDescription(ProductType.FISH);
        ProductDescription fruit = new ProductDescription(ProductType.FRUIT);
        ProductDescription alcohol = new ProductDescription(ProductType.ALCOHOL);
        ProductDescription tobacco = new ProductDescription(ProductType.TOBACCO);

        Product beef = new Product("Beef from the Farm", meat, 12.00);
        Product gin = new Product("Gordon's gin", alcohol, 18.00);
        Product tune = new Product("Tune Fish", fish, 7.00);
        Product apple = new Product("Red Queen apple", fruit, 3.00);
        Product orange = new Product("Sicilian Orange", fruit, 12.00);
        Product cigarette = new Product("Lucky Strike", tobacco, 6.50);
        Product tomato = new Product("Tomato Pera", vegetable, 1.50);

        //creating Warehouse
        Warehouse warehouse = new Warehouse();
        warehouse.getProductMap().put(beef, 20);
        warehouse.getProductMap().put(tune, 20);
        warehouse.getProductMap().put(gin, 10);
        warehouse.getProductMap().put(apple, 12);
        warehouse.getProductMap().put(orange, 10);
        warehouse.getProductMap().put(cigarette, 5);
        warehouse.getProductMap().put(tomato, 20);
    }

    public void startShopping(Client client) {
        Scanner sc = new Scanner(System.in);

        //start shopping
        System.out.println("Welcome to our shop!");
        System.out.println("Print F in any moment in order to finish");
        String reply = sc.nextLine();
        while (!reply.equalsIgnoreCase("F")) {
            Map<Product, Integer> currentProducts = shop.getActualWarehouse();
            System.out.println("Here you can see our products today:" + currentProducts);
            System.out.print("Select product: ");
            String selectedProductName = sc.nextLine();
            boolean productInSale = isProductInSale(currentProducts.keySet(), selectedProductName);
            if(!productInSale) {
                System.out.println("Sorry, we don't have this at the moment.\nPlease, select other product");
                continue;
            }

            Map.Entry<Product, Integer> productEntry = shop.getProductEntryByName(selectedProductName);
            int productCount;
            do {
                System.out.print("Select count of " + selectedProductName + ": ");
                productCount = sc.nextInt();

                if (productCount > productEntry.getValue() || productCount < 1) {
                    System.out.println("You entered wrong count. Try one more time");
                }
            } while (productCount > productEntry.getValue() || productCount < 1);


            client.addProductToBasket(productEntry.getKey(),productEntry.getValue());
        }
    }

    public void buyingProduct(Client client, Cashier cashier) {
        double totalPrice = cashier.sellProducts(client);
        boolean readyToPay = client.isReadyToPay();
        if(!readyToPay) {
            cashier.returnDeclinedProducts(client.getBasket(), shop);
        }
        client.payForProducts(totalPrice);
        double amount = cashier.receiveFromBuyer(client);
        shop.sendMoneyToBank(amount);
    }

    private boolean isProductInSale(Set<Product> products, String selectedProduct) {
        if(selectedProduct == null || selectedProduct.isBlank()) {
            return false;
        }
        for (Product product : products) {
            if (product.getName().equals(selectedProduct)) {
                return true;
            }
        }
        return false;
    }
}
