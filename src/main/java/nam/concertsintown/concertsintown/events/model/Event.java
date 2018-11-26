package nam.concertsintown.concertsintown.events.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Event {

    @Id
    private String uuid;
    private String title; // NOT NULL
    private String description;// NOT NULL
    private String city; // NOT NULL
    private Date date_start; // NOT NULL
    private Date date_end ;// NOT NULL
    private String image;
    private String link; // NOT NULL
    private String placename; // NOT NULL
    @Column(length = 10000)
    private String free_text;

    public Event() {}

    public Event(String uuid, String title, String description, String city, Date date_start, Date date_end, String image, String link, String placename, String free_text) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.city = city;
        this.date_start = date_start;
        this.date_end = date_end;
        this.image = image;
        this.link = link;
        this.placename = placename;
        this.free_text = free_text;
    }

    public Event(String title, String description, String city, Date date_start, Date date_end, String image, String link, String placename, String free_text) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.date_start = date_start;
        this.date_end = date_end;
        this.image = image;
        this.link = link;
        this.placename = placename;
        this.free_text = free_text;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

     public void setCity(String city) {
        this.city = city;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getFree_text() {
        return free_text;
    }

    public void setFree_text(String free_text) {
        this.free_text = free_text;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", placename='" + placename + '\'' +
                ", free_text='" + free_text + '\'' +
                '}';
    }
}
