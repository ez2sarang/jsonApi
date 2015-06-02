package com.fast2.zimin.navigator.bean.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ez2sarang on 15. 4. 9..
 */
@Entity
@Table(name = "ContentGroup")
public class ContentGroup {
    @Id
    private Long contentGroupId;
    private String title;
    private String summaryLong;
    private String actor;
    private String director;
    private String rating;
    private String displayRunTime;
    private String year;
    private String countryOfOrigin;
    private String genre;
    private String poster;

    @OneToMany(cascade={javax.persistence.CascadeType.ALL}, orphanRemoval = false)
    @JoinColumn(name = "contentGroupId", insertable=false, updatable=false)
    private List<ContentAsset> contentAssetList;

//    @Column(name = "contentGroupId", nullable = false, insertable = true, updatable = true)
    public long getContentGroupId() {
        return contentGroupId;
    }

    public void setContentGroupId(long contentGroupId) {
        this.contentGroupId = contentGroupId;
    }

//    @Basic
//    @Column(name = "title", nullable = true, insertable = true, updatable = true, length = 256)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @Basic
//    @Column(name = "summaryLong", nullable = true, insertable = true, updatable = true, length = 4096)
    public String getSummaryLong() {
        return summaryLong;
    }

    public void setSummaryLong(String summaryLong) {
        this.summaryLong = summaryLong;
    }

//    @Basic
//    @Column(name = "actor", nullable = true, insertable = true, updatable = true, length = 256)
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

//    @Basic
//    @Column(name = "director", nullable = true, insertable = true, updatable = true, length = 256)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

//    @Basic
//    @Column(name = "rating", nullable = true, insertable = true, updatable = true, length = 10)
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

//    @Basic
//    @Column(name = "displayRunTime", nullable = true, insertable = true, updatable = true, length = 10)
    public String getDisplayRunTime() {
        return displayRunTime;
    }

    public void setDisplayRunTime(String displayRunTime) {
        this.displayRunTime = displayRunTime;
    }

//    @Basic
//    @Column(name = "year", nullable = true, insertable = true, updatable = true, length = 10)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

//    @Basic
//    @Column(name = "countryOfOrigin", nullable = true, insertable = true, updatable = true, length = 10)
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

//    @Basic
//    @Column(name = "genre", nullable = true, insertable = true, updatable = true, length = 100)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

//    @Basic
//    @Column(name = "poster", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<ContentAsset> getContentAssetList() {
        return contentAssetList;
    }

    public void setContentAssetList(List<ContentAsset> contentAssetList) {
        this.contentAssetList = contentAssetList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentGroup that = (ContentGroup) o;

        if (contentGroupId != that.contentGroupId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (summaryLong != null ? !summaryLong.equals(that.summaryLong) : that.summaryLong != null) return false;
        if (actor != null ? !actor.equals(that.actor) : that.actor != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (displayRunTime != null ? !displayRunTime.equals(that.displayRunTime) : that.displayRunTime != null)
            return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (countryOfOrigin != null ? !countryOfOrigin.equals(that.countryOfOrigin) : that.countryOfOrigin != null)
            return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (poster != null ? !poster.equals(that.poster) : that.poster != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (contentGroupId ^ (contentGroupId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (summaryLong != null ? summaryLong.hashCode() : 0);
        result = 31 * result + (actor != null ? actor.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (displayRunTime != null ? displayRunTime.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (countryOfOrigin != null ? countryOfOrigin.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        return result;
    }
}
