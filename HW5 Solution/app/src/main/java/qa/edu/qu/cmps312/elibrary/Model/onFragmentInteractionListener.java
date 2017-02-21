package qa.edu.qu.cmps312.elibrary.Model;

import java.util.ArrayList;

/**
 * Created by sarahalhussaini on 11/14/16.
 */

public interface onFragmentInteractionListener {

    void displayBookInfo(Book book);
    void onBuyItBtnClicked(String url);
    void onAddToFavBtnClicked();
    void showFavorites();
}
