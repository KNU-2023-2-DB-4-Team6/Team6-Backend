package comp322.NCVS.form;

import lombok.Data;

@Data
public class RevenueForm {
    private int quantity;
    private String productId;
    private int total;

    public RevenueForm(){

    }

    public RevenueForm(int quantity, String productId, int total) {
        this.quantity = quantity;
        this.productId = productId;
        this.total = total;
    }
}
