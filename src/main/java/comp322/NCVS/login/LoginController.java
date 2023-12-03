package comp322.NCVS.login;

import comp322.NCVS.form.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm) throws SQLException {
        return loginService.login(loginForm);
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody LoginForm loginForm) throws SQLException {
        return loginService.signUp(loginForm);

    }

}
