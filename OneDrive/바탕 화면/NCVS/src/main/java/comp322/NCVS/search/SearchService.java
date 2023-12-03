package comp322.NCVS.search;

import comp322.NCVS.form.SearchForm;
import comp322.NCVS.repository.HasRepository;
import comp322.NCVS.repository.ProductRepository;
import comp322.NCVS.repository.StoreRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final ProductRepository productRepository;
    private final HasRepository hasRepository;
    private final StoreRepository storeRepository;

    public ArrayList<KeywordSearchForm> searchByKeyword(String keyword) throws SQLException {
        ArrayList<KeywordSearchForm> keywordArray = new ArrayList<>();
        for (String kS : productRepository.findByKeyword(keyword)){
            KeywordSearchForm keywordSearchForm = new KeywordSearchForm();
            keywordSearchForm.setName(kS);
            keywordArray.add(keywordSearchForm);
        }
        return keywordArray;
    }

    public ArrayList<SearchForm> searchByName(String Name) throws SQLException {
        String productId = productRepository.findIdByName(Name);
        ArrayList<SearchForm> searchInfo = hasRepository.findSQByProductId(productId);
        for (SearchForm searchForm : searchInfo){
            storeRepository.findLocById(searchForm);
        }
        return searchInfo;
    }
    @Data
    class KeywordSearchForm{
        private String Name;

        public KeywordSearchForm(){

        }

        public KeywordSearchForm(String name) {
            Name = name;
        }
    }
}
