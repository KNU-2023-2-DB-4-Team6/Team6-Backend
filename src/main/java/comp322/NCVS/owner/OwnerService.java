package comp322.NCVS.owner;

import comp322.NCVS.form.*;
import comp322.NCVS.repository.*;
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
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    public ArrayList<CVSForm> myCVS(String ownerId) throws SQLException {
        ArrayList<CVSForm> CVS = storeRepository.findByOwnerId(ownerId);
        for (CVSForm cvs : CVS){
            ArrayList<RevenueForm> revenueForms = paymentRepository.findQandP(cvs.getStoreId());
            int total = 0;
            for (RevenueForm revenueForm : revenueForms){
                total += productRepository.findCost(revenueForm);
            }
            cvs.setRevenue(total);
        }
        return CVS;
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
        orderForm.setOrderId(makeRandomId());
        orderRepository.save(orderForm);
        return "success";
    }

    public ArrayList<OrderForm> findOrder(String storeId) throws SQLException {
        ArrayList<OrderForm> orders = orderRepository.findByStoreId(storeId);
        for (OrderForm order : orders){
            productRepository.findNPCImageById(order);
        }
        return orders;
    }

    public ArrayList<TopProductForm> topProduct(String storeId) throws SQLException {
        ArrayList<TopProductForm> topProducts = paymentRepository.findTopProduct(storeId);
        for (TopProductForm topProduct : topProducts){
            productRepository.findTopProductInfo(topProduct);
        }
        return topProducts;
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
