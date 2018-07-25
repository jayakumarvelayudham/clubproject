package customer.mmpl.tcs.com.clubproject.modul;

/**
 * Created by BeAsT on 22-Mar-18.
 */

public class Movies {

    String title;
    String image;
    String price;

//    public Movies(String image) {
//        this.image = image;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
