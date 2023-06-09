package shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ProductType {
    ALCOHOL("Alcohol", "Different alcohol beverages"),
    TOBACCO("Tobacco", "Miscellaneous tobacco"),
    MEAT("Meat", "Variety of meat"),
    FISH("Fish", "A lot of fish"),
    FRUIT("Fruit", "Miscellaneous fruits"),
    VEGETABLE("Vegetable", "Miscellaneous vegetables");

    private String label;
    private String description;

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    ProductType(String label, String description) {
        this.label = label;
        this.description = description;
    }

//    public static ProductType fromLabel(String label) {
//        for (ProductType productType : ProductType.values()) {
//            if (productType.getLabel().equals(label)) {
//                return productType;
//            }
//        }
//        throw new IllegalArgumentException("No enum constant with label: " + label);
//    }
}
