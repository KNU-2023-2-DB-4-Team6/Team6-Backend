package comp322.NCVS.form;

import lombok.Data;

@Data
public class SearchForm {
    private String storeId;
    private String Name;
    private Double loc_x;
    private Double loc_y;
    private int quantity;

    public SearchForm(){

    }

    public SearchForm(String storeId, String name, Double loc_x, Double loc_y, int quantity) {
        this.storeId = storeId;
        Name = name;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
        this.quantity = quantity;
    }
}
