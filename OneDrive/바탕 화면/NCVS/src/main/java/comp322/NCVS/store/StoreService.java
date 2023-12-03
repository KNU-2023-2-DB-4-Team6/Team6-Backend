package comp322.NCVS.store;

import comp322.NCVS.form.OneStoreForm;
import comp322.NCVS.form.ProductForm;
import comp322.NCVS.repository.HasRepository;
import comp322.NCVS.repository.ProductRepository;
import comp322.NCVS.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {
    private final HasRepository hasRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ArrayList<ProductForm> allProduct(String storeId) throws SQLException {
        ArrayList<ProductForm> allProduct = hasRepository.findAllProductId(storeId);
        for (ProductForm product : allProduct){
            productRepository.findProductInfo(product);
        }
        return allProduct;
    }

    public OneStoreForm oneStore(String storeId) throws SQLException {
        return storeRepository.findById(storeId);
    }
}
