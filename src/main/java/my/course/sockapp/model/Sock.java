package my.course.sockapp.model;

import java.util.Objects;

public class Sock {
    private final Colour colour;
    private final Size size;
    private final int cottonPart;

    public Sock(Colour colour, Size size, int cottonPart) {
        this.colour = colour;
        this.size = size;
        this.cottonPart = cottonPart;
    }

    public Colour getColour() {
        return colour;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonPercentage() {
        return cottonPart;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cottonPart == sock.cottonPart && colour == sock.colour && size == sock.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour, size, cottonPart);
    }
}
