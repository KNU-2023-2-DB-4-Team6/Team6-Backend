package comp322.NCVS.form;

import lombok.Data;

@Data
public class ProductForm {
    private String productId;
    private String Name;
    private int Price;
    private String Category;
    private int Quantity;
    private boolean HasEvent;
    private boolean HasFavorite;
    private String Image_Url;

    public ProductForm(){

    }

    public ProductForm(String productId, String name, int price, String category, int quantity, boolean hasEvent, boolean hasFavorite, String image_Url) {
        this.productId = productId;
        Name = name;
        Price = price;
        Category = category;
        Quantity = quantity;
        HasEvent = hasEvent;
        HasFavorite = hasFavorite;
        Image_Url = image_Url;
    }
}
