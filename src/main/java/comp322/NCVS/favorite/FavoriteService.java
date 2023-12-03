package comp322.NCVS.favorite;

import comp322.NCVS.form.FavoriteForm;
import comp322.NCVS.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public ArrayList<FavoriteForm> findAllFavorite(String clientId) throws SQLException {
        return favoriteRepository.findAllFavorite(clientId);
    }

    public String favoriteModify(FavoriteForm favoriteForm) throws SQLException {
        if (favoriteRepository.isUniqueFavorite(favoriteForm) == true) {
            favoriteRepository.save(favoriteForm);
            log.info("new favorite insert");
            return "success";
        }else{
            favoriteRepository.delete(favoriteForm);
            log.info("favorite delete");
            return "success";
        }
    }
}
