package qa.edu.qu.cmps312.elibrary;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qa.edu.qu.cmps312.elibrary.Model.Book;
import qa.edu.qu.cmps312.elibrary.Model.Books;

/**
 * Created by sarahalhussaini on 11/21/16.
 */

public class FavoriteBooksFragment extends Fragment {

    public FavBooksAdapter favBooksAdapter;
    public ArrayList<Book> favBooks = new ArrayList<>();

    public FavoriteBooksFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav_books, container,false);

        ArrayList<Book> books = Books.getInstance().getData();
        for (Book book : books) { //filtering the array to get only fav items
            Log.i("TAG",String.valueOf(book.isFav()));
            if (book.isFav())
                favBooks.add(book);
        }

        favBooksAdapter = new FavBooksAdapter(getActivity(), R.layout.row_layout, favBooks);

        ListView listView = (ListView) view.findViewById(R.id.fav_books_listView);
        listView.setAdapter(favBooksAdapter);

        return view;
    }

    public class FavBooksAdapter extends ArrayAdapter<Book> {

        private Context mContext;
        private int mLayoutId;
        private ArrayList<Book> books = null;

        public FavBooksAdapter(Context context, int resource, List<Book> books) {
            super(context, resource, books);
            this.mContext = context;
            this.mLayoutId = resource;
            this.books = (ArrayList<Book>) books;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;

            if (row == null) { // if I don't have the row before
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                row = layoutInflater.inflate(mLayoutId, parent, false);
            }

            //get views
            ImageView bookImg = (ImageView) row.findViewById(R.id.book_imgview);
            TextView authorTxtView = (TextView) row.findViewById(R.id.author_name_textv);
            TextView titleTxtView = (TextView) row.findViewById(R.id.book_name_textv);

            Book theBook = getItem(position);

            bookImg.setImageResource(theBook.getBookImg());
            authorTxtView.setText(theBook.getAuthor());
            titleTxtView.setText(theBook.getTitle());


            return row;
        }
    }
}
