package qa.edu.qu.cmps312.elibrary.Model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sarahalhussaini on 11/23/16.
 */

public class Books {

    private ArrayList<Book> data;
    public ArrayList<Book>  getData() {return data;}
    public void setData(ArrayList<Book>  data) {this.data = data;}
    public int getIndex(Book book){
        for(Book aBook : this.data)
        {
            if(aBook.getTitle().equals(book.getTitle()))
                return this.data.indexOf(aBook);
        }
        return -1;
    }
    public void setFav(Book book){
        int idx = this.getIndex(book);
        this.data.get(idx).setFav(true);
    }

    private static final Books holder = new Books();
    public static Books getInstance() {return holder;}
}
