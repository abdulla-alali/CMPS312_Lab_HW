package qa.edu.qu.cmps312.elibrary;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import qa.edu.qu.cmps312.elibrary.Model.Book;
import qa.edu.qu.cmps312.elibrary.Model.onFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements onFragmentInteractionListener {

    private TextView mTV;
    private boolean isTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isTwoPane = getResources().getBoolean(R.bool.two_pane);
        mTV = (TextView) findViewById(R.id.frame_layout_tv);

    }

    @Override
    public void displayBookInfo(Book book) {

        if (book != null) {

            if (isTwoPane) {
                //landscape mode or tablet

                BookDetailsFragment fragment = new BookDetailsFragment();
                Bundle args = new Bundle();
                args.putParcelable(getResources().getString(R.string.BOOK), book);
                fragment.setArguments(args);
                mTV.setText("");

                launchFragment(fragment);
            } else { //it's a phone --> new Activity

                Intent intent = new Intent(this, BookDetailsActivity.class);
                intent.putExtra(getResources().getString(R.string.THE_CHOSEN_BOOK), book);
                startActivity(intent);
            }
        } else {
            Toast toast = Toast.makeText(this, "The book is not available", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    //will be invoked only if the phone is on landscape or it's a tablet
    @Override
    public void onBuyItBtnClicked(String url) {

        if (isTwoPane) { //landscape mode or tablet

            WebViewFragment fragment = new WebViewFragment();
            Bundle args = new Bundle();
            args.putString(getResources().getString(R.string.BOOK_URL), url);
            fragment.setArguments(args);

            launchFragment(fragment);
        }
    }

    @Override
    public void onAddToFavBtnClicked() {
        FavoriteBooksFragment fragment = new FavoriteBooksFragment();
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fav_book_dynamic_fragment, fragment).commit();

    }

    @Override
    public void showFavorites() {

        Intent intent = new Intent(this, BookDetailsActivity.class);
        startActivity(intent);
    }


    public void launchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.book_dynamic_fragment, fragment).commit();

    }

}
