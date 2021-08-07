package ru.suvorov.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "review_id")
    private Long review_id;
    private String text;
    private String title;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private  User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Review() {
    }

    public Review(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_id=" + review_id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return review_id.equals(review.review_id) &&
                title.equals(review.title) &&
                text.equals(review.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review_id, title, text);
    }
}
