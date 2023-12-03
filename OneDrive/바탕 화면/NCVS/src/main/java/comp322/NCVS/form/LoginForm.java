package comp322.NCVS.form;

import lombok.Data;

@Data
public class LoginForm {
    private String id;
    private String password;
    private String state;
    private Double loc_x;
    private Double loc_y;

    public LoginForm(){

    }

    public LoginForm(String id, String password, String state, Double loc_x, Double loc_y) {
        this.id = id;
        this.password = password;
        this.state = state;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
    }
}

