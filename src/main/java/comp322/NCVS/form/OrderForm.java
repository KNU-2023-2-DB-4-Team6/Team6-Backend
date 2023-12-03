package comp322.NCVS.form;

import lombok.Data;

@Data
public class OrderForm {
    private String orderId;
    private int product_quantity;
    private String arrival_time;
    private String arrival_state;
    private String ownerId;
    private String productId;

    public OrderForm(){

    }

    public OrderForm(String orderId, int product_quantity, String arrival_time, String arrival_state, String ownerId, String productId) {
        this.orderId = orderId;
        this.product_quantity = product_quantity;
        this.arrival_time = arrival_time;
        this.arrival_state = arrival_state;
        this.ownerId = ownerId;
        this.productId = productId;
    }
}
