package com.solvd.classes;

@Deprecated(since = "2023")
public final class Reflect {
    private String firstField;
    public int secondField;
    private final boolean thirdField = false;

    public Reflect(String firstField, int secondField) {
        this.firstField = firstField;
        this.secondField = secondField;
    }

    private Reflect() {
        this.firstField = null;
    }

    public boolean isThirdField() {
        return thirdField;
    }

    public int secondFieldOperation() {
        if (secondField > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getFirstField() {
        return firstField;
    }

    public void setFirstField(String firstField) {
        this.firstField = firstField;
    }

    @Override
    public String toString() {
        return "Reflect{" +
                "firstField='" + firstField + '\'' +
                ", secondField=" + secondField +
                ", thirdField=" + thirdField +
                '}';
    }
}
