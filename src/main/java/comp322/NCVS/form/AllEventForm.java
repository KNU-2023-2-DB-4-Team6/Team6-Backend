package comp322.NCVS.form;

import lombok.Data;

@Data
public class AllEventForm {
    private String EventName;
    private String policy;
    private String start;
    private String end;
    private String Image_Url;

    public AllEventForm(){

    }

    public AllEventForm(String eventName, String policy, String start, String end, String image_Url) {
        EventName = eventName;
        this.policy = policy;
        this.start = start;
        this.end = end;
        Image_Url = image_Url;
    }
}
