package comp322.NCVS.form;

import lombok.Data;

@Data
public class FavoriteForm {
    private String clientId;
    private String storeId;
    private String productId;

    public FavoriteForm(){

    }

    public FavoriteForm(String clientId, String storeId, String productId) {
        this.clientId = clientId;
        this.storeId = storeId;
        this.productId = productId;
    }
}
