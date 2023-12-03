package comp322.NCVS.favorite;

import comp322.NCVS.form.FavoriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FavoriteController {
    private final FavoriteService favoriteService;

    @GetMapping("/favorite")
    public ArrayList<FavoriteForm> findAllFavorite(@RequestParam("clientId") String clientId) throws SQLException {
        return favoriteService.findAllFavorite(clientId);
    }

    @PostMapping("/favorite/Modify")
    public String favoriteModify(@RequestBody FavoriteForm favoriteForm) throws SQLException {
        return favoriteService.favoriteModify(favoriteForm);
    }
}
