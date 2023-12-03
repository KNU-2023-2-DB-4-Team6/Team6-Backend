package comp322.NCVS.mainpage;

import comp322.NCVS.form.AllEventForm;
import comp322.NCVS.form.AllStoreForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainPageController {
    private final MainPageService mainPageService;

    @GetMapping("/mainPage/allStore")
    public ArrayList<AllStoreForm> allStore() throws SQLException {
        return mainPageService.allStore();
    }

    @GetMapping("/mainPage/allIngEvent")
    public ArrayList<AllEventForm> allIngEvent() throws SQLException {
        return mainPageService.allIngEvent();
    }
}
