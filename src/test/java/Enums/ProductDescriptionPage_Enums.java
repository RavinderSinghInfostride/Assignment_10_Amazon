package Enums;

public enum ProductDescriptionPage_Enums {
    ProductSizeVariation_Id("variation_size_name"),
    ProductColourVariation_Id("variation_color_name");

    private String name;

    private ProductDescriptionPage_Enums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}
