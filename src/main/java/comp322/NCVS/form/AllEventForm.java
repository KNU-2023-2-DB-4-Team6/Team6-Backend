package comp322.NCVS.form;

public class AllEventForm {
    private String EventName;
    private String policy;
    private String start;
    private String end;

    public AllEventForm(){

    }

    public AllEventForm(String eventName, String policy, String start, String end) {
        EventName = eventName;
        this.policy = policy;
        this.start = start;
        this.end = end;
    }

    public String getEventName() {
        return EventName;
    }

    public String getPolicy() {
        return policy;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
