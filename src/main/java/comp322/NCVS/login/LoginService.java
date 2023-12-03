package comp322.NCVS.login;

import comp322.NCVS.form.LoginForm;
import comp322.NCVS.repository.ClientRepository;
import comp322.NCVS.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final ClientRepository clientRepository;
    private final OwnerRepository ownerRepository;

    public String login(LoginForm loginForm) throws SQLException {
        String findPassword = null;
        if (loginForm.getState().equals("client")){
            findPassword = clientRepository.findPasswordById(loginForm.getId());
        } else if (loginForm.getState().equals("owner")) {
            findPassword = ownerRepository.findPasswordById(loginForm.getId());
        }
        if (findPassword == null){
            log.info("Id Not Exist Id : " + loginForm.getId());
            return "id incorrect";
        }else if(findPassword.equals(loginForm.getPassword())){
            log.info("login by Id : " + loginForm.getId());
            return "success";
        }else{
            log.info("password Not correct Id : " + loginForm.getId());
            return "password incorrect";
        }
    }

    public String signUp(LoginForm loginForm) throws SQLException {
        if (loginForm.getState().equals("client")) {
            if (clientRepository.findPasswordById(loginForm.getId()) == null) {
                clientRepository.save(makeRandomId(), loginForm);
                log.info("insert new client Id : " + loginForm.getId());
                return "success";
            }else{
                log.info("client id duplication error Id : " + loginForm.getId());
                return "error";
            }
        } else if (loginForm.getState().equals("owner")) {
            if (ownerRepository.findPasswordById(loginForm.getId()) == null) {
                ownerRepository.save(makeRandomId(), loginForm.getId(), loginForm.getPassword());
                log.info("insert new owner Id : " + loginForm.getId());
                return "success";
            }else{
                log.info("owner id duplication error Id : " + loginForm.getId());
                return "error";
            }
        }
        return null;
    }

    private static String makeRandomId() {
        String s = "";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(characters.length());
            s += characters.charAt(randomNumber);
        }
        return s;
    }

}
