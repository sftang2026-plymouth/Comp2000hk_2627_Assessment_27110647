package uk.ac.plymouth.hkuspace.sosbooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class DashboardViewModel extends ViewModel {
    private final MutableLiveData<List<SuggestionItem>> itemsSuggestion = new MutableLiveData<>();
    private final MutableLiveData<List<BookingItem>> itemUpcoming = new MutableLiveData<>();
    private final MutableLiveData<List<BookingItem>> itemWaiting = new MutableLiveData<>();

    public enum ItemType {
        ItemType_Date,
        ItemType_DateTime,
        ItemType_DateTimeRoom,

    }

    public class SuggestionItem {
        private final String title;
        private final String subtitle;
        private final ItemType type;

        private final String date;
        private final String time;
        private final String room;


        public SuggestionItem(String title, String subtitle, String date) {
            this.title = title;
            this.subtitle = subtitle;
            this.type = ItemType.ItemType_Date;
            this.date = date;
            this.time = "";
            this.room = "";
        }

        public SuggestionItem(String title, String subtitle, String date, String time) {
            this.title = title;
            this.subtitle = subtitle;
            this.type = ItemType.ItemType_DateTime;
            this.date = date;
            this.time = time;
            this.room = "";
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }
    }

    public class BookingItem {
        private final String date;
        private final String time;
        private final String room;

        public BookingItem(String date, String time, String room) {
            this.date = date;
            this.time = time;
            this.room = room;
        }

        public String getString() {
            return this.date + " " + this.time + "\n" + this.room;
        }
    }

    public LiveData<List<SuggestionItem>> getSuggestionItems() {
        return itemsSuggestion;
    }

    public LiveData<List<BookingItem>> getUpcomingItems() {
        return itemUpcoming;
    }
    public LiveData<List<BookingItem>> getWaitingItems() {
        return itemWaiting;
    }

    public DashboardViewModel() {
        loadItems();
    }

    public void loadItems() {
        List<SuggestionItem> listSuggestions = new ArrayList<SuggestionItem>();
        listSuggestions.add(new SuggestionItem("Today", "", "26-11-2026"));
        listSuggestions.add(new SuggestionItem("Tomorrow", "", "27-11-2026"));
        listSuggestions.add(new SuggestionItem("Next Monday", "15:00", "30-11-2026", "15:00"));
        itemsSuggestion.setValue(listSuggestions);

        List<BookingItem> listUpcoming = new ArrayList<BookingItem>();
        listUpcoming.add(new BookingItem("Today", "13:00", "M3"));
        listUpcoming.add(new BookingItem("Today", "16:00", "M2"));
        itemUpcoming.setValue(listUpcoming);

        List<BookingItem> listWaiting = new ArrayList<BookingItem>();
        listWaiting.add(new BookingItem("Friday", "15:00", "M1"));
        itemWaiting.setValue(listWaiting);
    }

}