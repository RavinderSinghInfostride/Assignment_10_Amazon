package Enums;

public enum HomePage_Enums {
    SearchBarInputBox("nav-search-field "),
    SearchButton("nav-right");

    private String name;

    private HomePage_Enums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}