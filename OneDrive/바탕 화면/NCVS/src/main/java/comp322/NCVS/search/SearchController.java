package comp322.NCVS.search;

import comp322.NCVS.form.SearchForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public ArrayList<SearchService.KeywordSearchForm> searchByKeyword(@RequestParam("keyword")String keyword) throws SQLException {
        return searchService.searchByKeyword(keyword);
    }

    @GetMapping("/search/quantity")
    public ArrayList<SearchForm> searchByName(@RequestParam("Name")String name) throws SQLException {
        return searchService.searchByName(name);
    }
}
