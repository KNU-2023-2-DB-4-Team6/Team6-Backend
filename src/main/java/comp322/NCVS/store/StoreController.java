package comp322.NCVS.store;

import comp322.NCVS.form.LoginForm;
import comp322.NCVS.form.OneStoreForm;
import comp322.NCVS.form.ProductForm;
import comp322.NCVS.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/store/allProduct")
    public ArrayList<ProductForm> allProduct(@RequestParam("storeId") String storeId) throws SQLException {
        return storeService.allProduct(storeId);
    }

    @GetMapping("/oneStore")
    public OneStoreForm oneStore(@RequestParam("storeId") String storeId) throws SQLException {
        return storeService.oneStore(storeId);
    }
}
