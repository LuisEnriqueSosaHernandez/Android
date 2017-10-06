package lesh.egresatecnm.com.recyclerandcardview.Modelos;

/**
 * Created by pc on 06/10/2017.
 */

public class Movie {
public String name;
    public int poster;

    public Movie(String name,int poster) {
        this.name=name;
        this.poster=poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
