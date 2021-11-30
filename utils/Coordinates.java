package utils;

public class Coordinates {
    private Integer x; //Значение поля должно быть больше -553, Поле не может быть null
    private Integer y; //Значение поля должно быть больше -444, Поле не может быть null

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return
                "x=" + x +
                ",y=" + y +";" ;
    }
}
