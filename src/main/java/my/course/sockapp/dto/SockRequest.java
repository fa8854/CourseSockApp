package my.course.sockapp.dto;

import my.course.sockapp.model.Colour;
import my.course.sockapp.model.Size;

public class SockRequest {
    private Colour colour;
    private Size size;
    private int cottonPercent;
    private int quantity;

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getCottonPercent() {
        return cottonPercent;
    }

    public void setCottonPercent(int cottonPercent) {
        this.cottonPercent = cottonPercent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
