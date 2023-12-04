package comp322.NCVS.form;

import lombok.Data;

@Data
public class OneStoreForm {
    private String storeId;
    private String Name;
    private String Address;
    private String PhoneNumber;
    private String Image_Url;

    public OneStoreForm(){

    }

    public OneStoreForm(String storeId, String name, String address, String phoneNumber, String image_Url) {
        this.storeId = storeId;
        Name = name;
        Address = address;
        PhoneNumber = phoneNumber;
        Image_Url = image_Url;
    }
}
