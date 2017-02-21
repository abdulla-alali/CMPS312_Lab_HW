package qa.edu.qu.cmps312.elibrary;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import qa.edu.qu.cmps312.elibrary.Model.Book;
import qa.edu.qu.cmps312.elibrary.Model.onFragmentInteractionListener;

public class BookDetailsActivity extends AppCompatActivity implements onFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Book aBook = (Book) getIntent().getParcelableExtra(getResources().getString(R.string.THE_CHOSEN_BOOK));
        ArrayList<Book> books = getIntent().getParcelableArrayListExtra("BOOKS");

        if (getResources().getConfiguration().orientation // No need for this activity if we're in landscape mode
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (aBook == null) {

            FavoriteBooksFragment fragment = new FavoriteBooksFragment();
            //Log.i("TAG","SIZE "+String.valueOf(books.size()));
            Bundle args = new Bundle();
            args.putParcelableArrayList("BOOK", books);
            fragment.setArguments(args);

            launchFragment(fragment);

        } else {

            if (savedInstanceState == null) { //first time launching this activity

                BookDetailsFragment fragment = new BookDetailsFragment();
                Bundle args = new Bundle();
                args.putParcelable(getResources().getString(R.string.BOOK), aBook);
                fragment.setArguments(args);

                launchFragment(fragment);
            }
        }

    }

    @Override
    public void displayBookInfo(Book book) {

    }

    //will be invoked only if it's a phone
    @Override
    public void onBuyItBtnClicked(String url) {

        boolean isTwoPane = getResources().getBoolean(R.bool.two_pane);

        if (!isTwoPane) {
            WebViewFragment fragment = new WebViewFragment();
            Bundle args = new Bundle();
            args.putString(getResources().getString(R.string.BOOK_URL), url);
            fragment.setArguments(args);
            launchFragment(fragment);
        }
    }

    @Override
    public void onAddToFavBtnClicked() {

    }

    @Override
    public void showFavorites() {

    }


    public void launchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.book_dynamic_fragment, fragment).commit();

    }

}
