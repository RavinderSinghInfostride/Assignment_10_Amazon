package Enums;

public enum SearchProductDisplayPage_Enums {
    ProductName("Apple iPhone 13 (128GB) - Blue");

    private String name;

    private SearchProductDisplayPage_Enums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}