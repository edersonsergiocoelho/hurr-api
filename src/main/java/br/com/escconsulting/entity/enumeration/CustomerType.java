package br.com.escconsulting.entity.enumeration;

public enum CustomerType {
    CUSTOMER("CUSTOMER"),
    OWNER("OWNER");

    private final String name;

    CustomerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
