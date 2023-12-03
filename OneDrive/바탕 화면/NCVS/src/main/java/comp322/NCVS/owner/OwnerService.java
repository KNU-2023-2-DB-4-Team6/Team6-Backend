package comp322.NCVS.owner;

import comp322.NCVS.form.CVSForm;
import comp322.NCVS.form.LoginForm;
import comp322.NCVS.form.OrderForm;
import comp322.NCVS.repository.ClientRepository;
import comp322.NCVS.repository.OrderRepository;
import comp322.NCVS.repository.OwnerRepository;
import comp322.NCVS.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerService {
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;

    public ArrayList<String[]> myCVS(String ownerId) throws SQLException {
        return storeRepository.findByOwnerId(ownerId);
    }

    public String addCVS(CVSForm cvsinfo) throws SQLException {
        String newStoreId = makeRandomId();
        cvsinfo.setStoreId(newStoreId);
        log.info("new CVS insert StoreId : " + newStoreId + "To Owner Id : " + cvsinfo.getOwnerId());
        storeRepository.save(cvsinfo);
        return "success";
    }

    public String deleteCVS(String storeId) throws SQLException {
        storeRepository.delete(storeId);
        log.info("Store delete storeId : " + storeId);
        return "success";
    }

    public String addOrder(OrderForm orderForm) throws SQLException {
        log.info("new order insert");
        orderRepository.save(orderForm);
        return "success";
    }

    public ArrayList<OrderForm> findOrder(String storeId) throws SQLException {
        return orderRepository.findByStoreId(storeId);
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
