package qa.edu.qu.cmps312.elibrary.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by sarahalhussaini on 11/14/16.
 */

public class Book implements Parcelable {

    private String title;
    private String author;
    private double price;
    private String overview;
    private String isbn;
    private String publicationDate;
    private int pages;
    private int bookImg;
    private String url;
    private boolean isFav = false;

    public Book() {
    }

    public Book(String title, String author, double price, String overview, String isbn, String publicationDate, int pages, int bookImg, String url) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.overview = overview;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.pages = pages;
        this.bookImg = bookImg;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getBookImg() {
        return bookImg;
    }

    public void setBookImg(int bookImg) {
        this.bookImg = bookImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.author);
        parcel.writeDouble(this.price);
        parcel.writeString(this.overview);
        parcel.writeString(this.isbn);
        parcel.writeString(this.publicationDate);
        parcel.writeInt(this.pages);
        parcel.writeInt(this.bookImg);
        parcel.writeString(this.url);
        parcel.writeByte((byte) (this.isFav ? 1 : 0));
    }

    protected Book(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.price = in.readDouble();
        this.overview = in.readString();
        this.isbn = in.readString();
        this.publicationDate = in.readString();
        this.pages = in.readInt();
        this.bookImg = in.readInt();
        this.url = in.readString();
        this.isFav = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }


    };

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
