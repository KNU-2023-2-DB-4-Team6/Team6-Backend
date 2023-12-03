package comp322.NCVS.form;

import lombok.Data;

@Data
public class OneStoreForm {
    private String storeId;
    private String Name;
    private String Address;
    private String PhoneNumber;

    public OneStoreForm(){

    }

    public OneStoreForm(String storeId, String name, String address, String phoneNumber) {
        this.storeId = storeId;
        Name = name;
        Address = address;
        PhoneNumber = phoneNumber;
    }
}
