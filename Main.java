package shop;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Shopping newShoppingDay = new Shopping();
        Shop shop = newShoppingDay.initializeShoppingBase();
        Client newClient = newShoppingDay.getNewClient();
        System.out.println("Dear " + newClient.getName() + ", welcome to our shop!");
        newShoppingDay.initializeShoppingProcess(newClient, shop);
        Map<Product, Integer> clientBasket = newClient.getBasket();
        if(clientBasket == null) {
            System.out.println("You didn't select any product, good bye");
        }
        newShoppingDay.processShopping(newClient, shop);
        System.out.println("******************************");
        System.out.println("Good bye, we hope you will come back again!");
        System.out.println("******************************");
    }
}
