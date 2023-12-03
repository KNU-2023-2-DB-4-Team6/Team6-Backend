package comp322.NCVS.form;

import lombok.Data;

@Data
public class AllStoreForm {
    private String store_id;
    private String loc_x;
    private String loc_y;

    public AllStoreForm(){
    }

    public AllStoreForm(String store_id, String loc_x, String loc_y) {
        this.store_id = store_id;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
    }
}
