package comp322.NCVS.form;

import lombok.Data;

@Data
public class FavoriteForm {
    private String clientId;
    private String storeId;
    private String storeName;
    private String productId;
    private String productName;
    private int price;
    private String category;
    private int quantity;
    private String arrivalTime;
    private String Image_Url;

    public FavoriteForm(){

    }

    public FavoriteForm(String clientId, String storeId, String storeName, String productId, String productName, int price, String category, int quantity, String arrivalTime, String image_Url) {
        this.clientId = clientId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.arrivalTime = arrivalTime;
        Image_Url = image_Url;
    }

}
