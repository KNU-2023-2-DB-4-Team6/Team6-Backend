package comp322.NCVS.mainpage;

import comp322.NCVS.form.AllEventForm;
import comp322.NCVS.form.AllStoreForm;
import comp322.NCVS.repository.EventRepository;
import comp322.NCVS.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor

public class MainPageService {
    private final StoreRepository storeRepository;
    private final EventRepository eventRepository;

    public ArrayList<AllStoreForm> allStore() throws SQLException {
        return storeRepository.findAllStore();
    }

    public ArrayList<AllEventForm> allIngEvent() throws SQLException {
        long nowMill = System.currentTimeMillis();
        ArrayList<AllEventForm> ingEvent = new ArrayList<>();
        for (AllEventForm event : eventRepository.findAllEvent()){
            if (stringDateToMill(event.getStart()) <= nowMill && nowMill <= stringDateToMill(event.getEnd())){
                ingEvent.add(event);
            }
        }
        return ingEvent;
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
