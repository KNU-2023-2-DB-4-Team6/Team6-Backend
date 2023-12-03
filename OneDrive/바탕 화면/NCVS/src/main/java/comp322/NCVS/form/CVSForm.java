package comp322.NCVS.form;

import lombok.Data;

@Data
public class CVSForm {
    private String StoreId;
    private String NAME;
    private String Address;
    private String PhoneNumber;
    private String OwnerId;
    private Double loc_x;
    private Double loc_y;

    public CVSForm(){

    }

    public CVSForm(String storeId, String NAME, String address, String phoneNumber, String ownerId, Double loc_x, Double loc_y) {
        StoreId = storeId;
        this.NAME = NAME;
        Address = address;
        PhoneNumber = phoneNumber;
        OwnerId = ownerId;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
    }
}
