package comp322.NCVS.form;

import lombok.Data;

@Data
public class TopProductForm {
    private String productId;
    private String Name;
    private String category;
    private int price;
    private String Image_Url;
    private int quantity;

    public TopProductForm(){

    }

    public TopProductForm(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
