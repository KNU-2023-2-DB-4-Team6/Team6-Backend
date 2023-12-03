package comp322.NCVS.owner;

import comp322.NCVS.form.CVSForm;
import comp322.NCVS.form.LoginForm;
import comp322.NCVS.form.OrderForm;
import comp322.NCVS.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/myCVS")
    public ArrayList<String[]> myCVS(@RequestParam("ownerId") String ownerId) throws SQLException {
        return ownerService.myCVS(ownerId);
    }

    @PostMapping("/myCVS/add")
    public String addCVS(@RequestBody CVSForm cvsInfo) throws SQLException {
        return ownerService.addCVS(cvsInfo);
    }

    @GetMapping("/myCVS/delete")
    public String deleteCVS(@RequestParam("storeId") String storeId) throws SQLException {
        return ownerService.deleteCVS(storeId);
    }

    @PostMapping("/myCVS/order/add")
    public String addOrder(@RequestBody OrderForm orderForm) throws SQLException {
        return ownerService.addOrder(orderForm);
    }

    @GetMapping("/myCVS/order")
    public ArrayList<OrderForm> CVSorder(@RequestParam("storeId") String storeId) throws SQLException {
        return ownerService.findOrder(storeId);
    }
}
