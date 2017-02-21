package qa.edu.qu.cmps312.elibrary;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
//import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.widget.SearchView;

import qa.edu.qu.cmps312.elibrary.Model.*;

/**
 * Created by sarahalhussaini on 11/14/16.
 */

public class BooksListFragment extends Fragment {

    private onFragmentInteractionListener mListener;
    public ArrayList<Book> books = new ArrayList<>();
    public BooksAdapter booksAdapter;
    private SearchView mSearchView;
    private boolean isTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mListener == null){
            try {
                mListener = (onFragmentInteractionListener) getActivity();
            } catch (ClassCastException e) {
                throw new ClassCastException(mListener.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        isTwoPane = getResources().getBoolean(R.bool.two_pane);

    }

    public BooksListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books_list, null);

        this.getBooks();
        books = Books.getInstance().getData();
        booksAdapter = new BooksAdapter(getActivity(), R.layout.row_layout, books);

        ListView listView = (ListView) view.findViewById(R.id.books_list_view);
        listView.setAdapter(booksAdapter);

        mSearchView = (SearchView) view.findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                for (Book aBook : books){
                    String title = aBook.getTitle().toLowerCase();

                    if (title.contains(s)){
                        mListener.displayBookInfo(aBook);
                        return true;
                    }
                }

                mListener.displayBookInfo(null);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        Button favBtn = (Button) view.findViewById(R.id.favBtn);
        if (!isTwoPane) { //will show this btn only if it's a phone
            favBtn.setVisibility(View.VISIBLE);
        }
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.showFavorites();
            }
        });

        return view;
    }

    public void getBooks() {

        String[] book_titles = getResources().getStringArray(R.array.book_titles);
        String[] authors = getResources().getStringArray(R.array.book_authors);
        TypedArray prices = getResources().obtainTypedArray(R.array.prices);
        String[] overviews = getResources().getStringArray(R.array.overviews);
        String[] isbns = getResources().getStringArray(R.array.isbns);
        String[] dates = getResources().getStringArray(R.array.pub_dates);
        int[] pages = getResources().getIntArray(R.array.pages);
        String[] urls = getResources().getStringArray(R.array.urls);
        TypedArray book_imgs = getResources().obtainTypedArray(R.array.book_images);

        for (int i = 0; i < book_titles.length; i++) {

            books.add(new Book(book_titles[i], authors[i], prices.getFloat(i,-1), overviews[i],
                    isbns[i], dates[i], pages[i], book_imgs.getResourceId(i, -1),urls[i]));

        }

        Books.getInstance().setData(books);
    }

    public class BooksAdapter extends ArrayAdapter<Book> {

        private Context mContext;
        private int mLayoutId;
        private ArrayList<Book> books = null;

        public BooksAdapter(Context context, int resource, List<Book> books) {
            super(context, resource, books);
            this.mContext = context;
            this.mLayoutId = resource;
            this.books = (ArrayList<Book>) books;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;

            if (row == null){ // if I don't have the row before
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                row = layoutInflater.inflate(mLayoutId,parent,false);
            }

            //get views

            ImageView bookImg = (ImageView) row.findViewById(R.id.book_imgview);
            TextView authorTxtView = (TextView) row.findViewById(R.id.author_name_textv);
            TextView titleTxtView = (TextView) row.findViewById(R.id.book_name_textv);

            final Book theBook = getItem(position);

            bookImg.setImageResource(theBook.getBookImg());
            authorTxtView.setText(theBook.getAuthor());
            titleTxtView.setText(theBook.getTitle());

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mListener.displayBookInfo(theBook);
                }
            });

            return row;
        }
    }
}
