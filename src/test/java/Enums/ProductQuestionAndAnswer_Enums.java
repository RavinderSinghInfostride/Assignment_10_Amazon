package Enums;

public enum ProductQuestionAndAnswer_Enums {
    Questions_Id1("question-Tx16XE7ZUPXZZO8"),
    Questions_Id2("question-Tx116VGZ58I4Q2H"),
    Questions_Id3("question-Tx117JH8YELZM25");

    private String name;

    private ProductQuestionAndAnswer_Enums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}