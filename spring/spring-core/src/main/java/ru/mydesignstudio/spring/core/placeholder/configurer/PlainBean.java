package ru.mydesignstudio.spring.core.placeholder.configurer;

public class PlainBean {
    private String value;

    public PlainBean(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
