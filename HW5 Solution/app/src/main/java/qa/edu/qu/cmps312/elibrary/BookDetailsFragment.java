package qa.edu.qu.cmps312.elibrary;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import qa.edu.qu.cmps312.elibrary.Model.Book;
import qa.edu.qu.cmps312.elibrary.Model.Books;
import qa.edu.qu.cmps312.elibrary.Model.onFragmentInteractionListener;

/**
 * Created by sarahalhussaini on 11/14/16.
 */

public class BookDetailsFragment extends Fragment {

    private Book mBook;
    private onFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBook = getArguments().getParcelable(getResources().getString(R.string.BOOK));
        }

        if (mListener == null){
            try {
                mListener = (onFragmentInteractionListener) getActivity();
            } catch (ClassCastException e) {
                throw new ClassCastException(mListener.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_details, container, false);

        ImageView bookImg = (ImageView) view.findViewById(R.id.book_imgview);
        TextView title = (TextView) view.findViewById(R.id.book_name_textv);
        TextView author = (TextView) view.findViewById(R.id.author_name_textv);
        TextView overview = (TextView) view.findViewById(R.id.overview_placeholder);
        TextView price = (TextView) view.findViewById(R.id.price_textv);
        TextView isbn = (TextView) view.findViewById(R.id.isbn_textv);
        TextView pubDate = (TextView) view.findViewById(R.id.date_textv);
        TextView pages = (TextView) view.findViewById(R.id.pages_textv);

        overview.setMovementMethod(new ScrollingMovementMethod()); //makes the text view scrollable

        bookImg.setImageResource(mBook.getBookImg());
        title.setText(mBook.getTitle());
        author.setText("by " + mBook.getAuthor());
        overview.setText(mBook.getOverview());
        price.setText(getResources().getString(R.string.price) + " $" + String.valueOf(new DecimalFormat("#.##").format(mBook.getPrice())));
        isbn.setText(getResources().getString(R.string.isbn) + mBook.getIsbn());
        pubDate.setText(getResources().getString(R.string.publication_date) + mBook.getPublicationDate());
        pages.setText(getResources().getString(R.string.pages) + String.valueOf(mBook.getPages()));

        Button buyItButton = (Button) view.findViewById(R.id.butItBtn);
        buyItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBuyItBtnClicked(mBook.getUrl());
            }
        });

        Button addToFavBtn = (Button) view.findViewById(R.id.addToFavBtn);
        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText((Context) mListener, "Book added to favorites", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                mBook.setFav(true);

//                ArrayList<Book> books = Books.getInstance().getData();
//                int idx = Books.getInstance().getIndex(mBook);
//                books.set(idx,mBook);
//                Books.getInstance().setData(books);

                Books.getInstance().setFav(mBook);
                mListener.onAddToFavBtnClicked();

            }
        });

        return view;
    }
}
