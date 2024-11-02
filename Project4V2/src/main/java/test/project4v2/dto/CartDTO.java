package test.project4v2.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private List<CartItemDTO> items;

    public CartDTO() {
        this.items = new ArrayList<>();
    }
    public List<CartItemDTO> getItems() {
        return items;
    }
    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }


    public void clear() {
        items.clear();
    }
}
