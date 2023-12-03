package comp322.NCVS.form;

import lombok.Data;

@Data
public class ProductForm {
    private String productId;
    private String Name;
    private int Price;
    private String Category;
    private int Quantity;

    public ProductForm(){

    }

    public ProductForm(String productId, String name, int price, String category, int quantity) {
        this.productId = productId;
        Name = name;
        Price = price;
        Category = category;
        Quantity = quantity;
    }

}
