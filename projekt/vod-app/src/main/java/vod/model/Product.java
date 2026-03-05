package vod.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int id;
    private String name;
    private String type;//url
    private Baker baker;//relacja do rezysera - kolejny obiekt danych w uproszczeniu założenie że jeden film ma 1 reżysera
    private float rating;//rating
    private List<Bakery> bakeries = new ArrayList<>();
//relacja wiele do wiele - bidirectional

    public Product(int id, String name, String type, Baker baker, float rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.baker = baker;
        this.rating = rating;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Baker getBaker() {
        return baker;
    }

    public void setBaker(Baker baker) {
        this.baker = baker;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Bakery> getBakeries() {
        return bakeries;
    }

    public void setBakeries(List<Bakery> bakeries) {
        this.bakeries = bakeries;
    }

    public void addBakeries(Bakery c) {
        this.bakeries.add(c);
    }


   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (Float.compare(movie.rating, rating) != 0) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        return poster != null ? poster.equals(movie.poster) : movie.poster == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }*/

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", baker=" + baker +
                ", rating=" + rating +
                '}';
    }
}
