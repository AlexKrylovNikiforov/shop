package shop;

public class ProductDescription {

    private ProductType productType;

    public ProductDescription(ProductType productType) {
        this.productType = productType;
           }

//    public void setProductType(ProductType productType) {
//        this.productType = productType;
//    }
//
    public ProductType getProductType() {
        return productType;
    }

}
