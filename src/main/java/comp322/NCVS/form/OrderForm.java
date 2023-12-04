package comp322.NCVS.form;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderForm {
    private String orderId;
    private String storeId;
    private int product_quantity;
    private String arrival_time;
    private String arrival_state;
    private String ownerId;
    private String productId;
    private String productName;
    private int productPrice;
    private String category;
    private String Image_Url;

    public OrderForm(){

    }


    public OrderForm(String orderId, String storeId, int product_quantity, String arrival_time, String arrival_state, String ownerId, String productId, String productName, int productPrice, String category, String image_Url) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.product_quantity = product_quantity;
        this.arrival_time = arrival_time;
        this.arrival_state = arrival_state;
        this.ownerId = ownerId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.category = category;
        Image_Url = image_Url;
    }
}
