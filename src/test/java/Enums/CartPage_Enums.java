package Enums;

public enum CartPage_Enums {
    ProductQuantityFour("quantity_4");

    private String name;

    private CartPage_Enums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}