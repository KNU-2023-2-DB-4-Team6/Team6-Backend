package comp322.NCVS.form;

import lombok.Data;

@Data
public class BuyForm {
    private String storeId;
    private String productId;
    private int quantity;

    public BuyForm(){

    }

    public BuyForm(String storeId, String productId, int quantity) {
        this.storeId = storeId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
