package comp322.NCVS.favorite;

import comp322.NCVS.form.FavoriteForm;
import comp322.NCVS.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final HasRepository hasRepository;
    private final OrderRepository orderRepository;

    public ArrayList<FavoriteForm> findAllFavorite(String clientId) throws SQLException {
        ArrayList<FavoriteForm> FavoriteProducts = favoriteRepository.findAllFavorite(clientId);
        for (FavoriteForm favoriteProduct : FavoriteProducts){
            storeRepository.findNameById(favoriteProduct);
            productRepository.findInfoById(favoriteProduct);
            hasRepository.findQuantityById(favoriteProduct);
            if (favoriteProduct.getQuantity() == 0){
                for (String arrive_time : orderRepository.findArrivalTime(favoriteProduct.getStoreId(), favoriteProduct.getProductId())){
                    if (System.currentTimeMillis() < stringDateToMill(arrive_time)){
                        favoriteProduct.setArrivalTime(arrive_time);
                        break;
                    }
                }
            }
        }
        return FavoriteProducts;


    }

    public String favoriteModify(FavoriteForm favoriteForm) throws SQLException {
        if (favoriteRepository.isFavorite(favoriteForm.getClientId(), favoriteForm.getStoreId(), favoriteForm.getProductId()) == false) {
            favoriteRepository.save(favoriteForm);
            log.info("new favorite insert");
            return "success";
        }else{
            favoriteRepository.delete(favoriteForm);
            log.info("favorite delete");
            return "success";
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
}
