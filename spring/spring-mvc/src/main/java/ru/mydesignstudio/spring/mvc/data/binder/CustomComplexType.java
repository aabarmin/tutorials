package ru.mydesignstudio.spring.mvc.data.binder;

public class CustomComplexType {
    private int year;
    private int month;
    private int day;

    public CustomComplexType(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
