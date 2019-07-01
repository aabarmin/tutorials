package ru.mydesignstudio.spring.oxm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Settings {
    private int id;
    private String alias;
    private String value;

    public Settings() {
    }

    public Settings(int id, String alias, String value) {
        this.id = id;
        this.alias = alias;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
