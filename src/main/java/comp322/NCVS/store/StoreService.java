package comp322.NCVS.store;

import comp322.NCVS.form.AllEventForm;
import comp322.NCVS.form.BuyForm;
import comp322.NCVS.form.OneStoreForm;
import comp322.NCVS.form.ProductForm;
import comp322.NCVS.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {
    private final HasRepository hasRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ContainRepository containRepository;
    private final EventRepository eventRepository;
    private final FavoriteRepository favoriteRepository;
    private final PaymentRepository paymentRepository;

    public ArrayList<ProductForm> allProduct(String storeId, String clientId) throws SQLException {
        ArrayList<ProductForm> allProduct = hasRepository.findAllProductId(storeId);
        for (ProductForm product : allProduct){
            productRepository.findProductInfo(product);
            product.setHasEvent(false);
            product.setHasFavorite(favoriteRepository.isFavorite(clientId, storeId, product.getProductId()));
            for (String eventId : containRepository.findById(product.getProductId())){
                long startMill = stringDateToMill(eventRepository.findById(eventId).getStart());
                long endMill = stringDateToMill(eventRepository.findById(eventId).getEnd());
                long now = System.currentTimeMillis();
                if (startMill <= now && now <= endMill){
                    product.setHasEvent(true);
                    break;
                }
            }
        }
        return allProduct;
    }

    public OneStoreForm oneStore(String storeId) throws SQLException {
        return storeRepository.findById(storeId);
    }

    public ArrayList<AllEventForm> productEvent(String productId) throws SQLException {
        ArrayList<AllEventForm> allEvents = new ArrayList<>();
        for (String eventId : containRepository.findById(productId)){
            AllEventForm event = eventRepository.findById(eventId);
            long startMill = stringDateToMill(event.getStart());
            long endMill = stringDateToMill(event.getEnd());
            long now = System.currentTimeMillis();
            if (startMill <= now && now <= endMill){
                allEvents.add(event);
            }
        }
        return allEvents;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String buy(BuyForm buyForm) throws SQLException {
        int newQuantity = hasRepository.isPurchasable(buyForm);
        log.info("newQuantity : " + newQuantity + " buyQuantity : " + buyForm.getQuantity());
        log.info("ProductId : " + buyForm.getProductId() + "StoreId : " + buyForm.getStoreId());
        if (newQuantity >= 0){
            hasRepository.updateBuy(newQuantity, buyForm);
            paymentRepository.save(makeRandomId(), buyForm);
            log.info("buy successful");
            return "success";
        }else{
            log.info("buy error");
            return "error";
        }
    }

    private long stringDateToMill(String stringDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date date = dateFormat.parse(stringDate);
            return date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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
