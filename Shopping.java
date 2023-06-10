package shop;

import java.util.*;

public class Shopping {
    private List<Client> clientList = new ArrayList<>();
    private Shop shop;

    public static void main(String[] args) {

    }


    private void initializeShoppingProcess() {
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

        List<Product> productsList = new ArrayList<>();
        productsList.add(beef);
        productsList.add(gin);
        productsList.add(tune);
        productsList.add(apple);
        productsList.add(orange);
        productsList.add(cigarette);
        productsList.add(tomato);

        //creating cashiers
        Set<ProductType> productTypesCarlos = new HashSet<>();
        Set<ProductType> productTypesJohanna = new HashSet<>();
        productTypesCarlos.add(meat.getProductType());
        productTypesCarlos.add(fish.getProductType());
        productTypesCarlos.add(vegetable.getProductType());
        productTypesCarlos.add(fruit.getProductType());
        productTypesJohanna.add(alcohol.getProductType());
        productTypesJohanna.add(tobacco.getProductType());
        List<Cashier> currentCashiers = new ArrayList<>();
        Cashier carlos = new Cashier("Carlos", 12, productTypesCarlos);
        Cashier johanna = new Cashier("Johanna", 7, productTypesJohanna);
        currentCashiers.add(carlos);
        currentCashiers.add(johanna);

        //initializing shop
        this.shop = new Shop(currentCashiers);
        shop.setBank(5000.00);

        //shop initializes warehouse inside itself
        Map<Product, Integer> currentProductMap = shop.createProductMap(productsList);
        shop.initializeWarehouse(currentProductMap);

    }

// тоже перенести в Shop, разделить на части
    public void initializeShoppingProcess(Client client, Shop shop) {
        Scanner sc = new Scanner(System.in);
        Map<Product, Integer> currentProducts = shop.getActualProductMap();
        System.out.println("Welcome to our shop!");
        System.out.println("Print F in any moment in order to finish");
        String reply = sc.nextLine();
        while (!reply.equalsIgnoreCase("F")) {
            System.out.println("Here you can see our products today:" + currentProducts);
            System.out.print("Select product: ");
            String selectedProductName = sc.nextLine();
            boolean productInSale = isProductInSale(currentProducts.keySet(), selectedProductName);
            if(!productInSale) {
                System.out.println("Sorry, we don't have this at the moment.\nPlease, select other product");
                continue;
            }

            int productCount;
            for(Map.Entry<Product, Integer> entry: client.getBasket().entrySet()) {
            do {
                System.out.print("Select count of " + selectedProductName + ": ");
                productCount = sc.nextInt();

                if (productCount > entry.getValue() || productCount < 1) {
                    System.out.println("You entered wrong count. Try one more time");
                }
            } while (productCount > entry.getValue() || productCount < 1);
            client.addProductToBasket(entry.getKey(),entry.getValue());
            }
        }
    }
// переделать. Перенести performBuyingProcess в Shop, выделить первую часть в private method assignCashiersToBuyer,
// он вернет мапу кассир: мапа продуктов. Для каждого из кассиров вызываем scanProducts, в Shop делаем метод sellProducts
// далее уже в Shopping вызываем
// метод performBuyingProcess и потом запрашиваем готовность платить. Если готов - вызываем payForProducts у client,
// добавляем деньги в банк, перемещаем товары со склада, очищаем корзину клиента, если нет - просто очищаем корзину клиента и прощаемся
    public void performShoppingProcess(Client client, Shop shop) {
        List<Product> currentProducts = new ArrayList<>();
        for(Map.Entry<Product, Integer> entry:client.getBasket().entrySet()) {
            Product currentProduct = entry.getKey();
            currentProducts.add(currentProduct);
            List<Cashier> availableCashiers = shop.getAvailableCashiers(currentProducts);
            if(!availableCashiers.isEmpty()) {
                for (Cashier cashier : availableCashiers) {
                    Map<Cashier, Map<Product,Integer>> products = new HashMap<>();
                    Map<Product, Integer> productsToScan = shop.assignProductsToCashier(client, cashier);
                    products.put(cashier, productsToScan);
                }
            }
        }

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
