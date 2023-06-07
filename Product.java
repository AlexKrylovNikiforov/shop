package shop;

public class Product {
    private String name;
    private ProductDescription productDescription;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(String name, ProductDescription productDescription, double price) {
        this.name = name;
        this.productDescription = productDescription;
        this.price = price;
    }


//    public Product(String name, ProductDescription productDescription) {
//        this.name = name;
//        this.productDescription = productDescription;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product: " + name + "\n" +
                "Price: " + price + " USD\n";
    }
}
