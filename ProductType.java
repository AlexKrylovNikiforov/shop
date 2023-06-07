package shop;

public enum ProductType {
    FRUIT("Fruit", "Miscellaneous fruits"),
    MEAT("Meat", "Variety of meat"),
    ALCOHOL("Alcohol", "Different alcohol beverages"),
    FISH("Fish", "A lot of fish"),
    TOBACCO("Tobacco", "Miscellaneous tobacco"),
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

    public static ProductType fromLabel(String label) {
        for (ProductType productType : ProductType.values()) {
            if (productType.getLabel().equals(label)) {
                return productType;
            }
        }
        throw new IllegalArgumentException("No enum constant with label: " + label);
    }
}
