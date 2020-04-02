package ru.mydesignstudio.spring.aop.proxy.factory;

public class PersonImpl implements Person {
    private String firstName;
    private String lastName;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
