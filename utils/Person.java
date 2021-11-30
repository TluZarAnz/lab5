package utils;

import java.time.LocalDate;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate birthday; //Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    public Person(String name, LocalDate birthday, Color eyeColor, Color hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday ;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }


    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return
                "(name='" + name + '\'' +
                ",birthday=" + birthday +
                ",eyeColor=" + eyeColor +
                ",hairColor=" + hairColor +
                ",nationality=" + nationality +
                ')';
    }
}
