package com.stepdevstep.dbexam.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

@Entity
@Table(name = "audiobooks")
public class Audiobook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id",unique = true) @NotNull
    private int book_id = -1;
    @Column(name = "name") @NotNull
    private String name;
    @Column(name = "genre") @NotNull
    private String genre;
    @Column(name = "author") @NotNull
    private String author;
    @Column(name = "speaker") @NotNull
    private String speaker;
    @Column(name = "duration") @NotNull
    private String duration;
    @Column(name = "views") @NotNull
    private int views = 0;
    @Column(name = "rate") @NotNull
    private float rate = 0.0f;
    @Column(name = "description")
    private String description;
    @Column(name = "cover")
    private String cover;
    @Column(name = "content")
    private String content;

    public Audiobook(){}
    public Audiobook(String name, String genre, String author,
                     String speaker, String duration, String description, String cover, String content) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.speaker = speaker;
        this.duration = duration;
        this.description = description;
        this.cover = cover;
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr) {
        this.description = descr;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %25d\n","Audiobook ID:", getBook_id()));
        sb.append(String.format("%-20s %25.25s\n","Name:", getName()));
        sb.append(String.format("%-20s %25.25s\n","Genre:", getGenre()));
        sb.append(String.format("%-20s %25.25s\n","Author:", getAuthor()));
        sb.append(String.format("%-20s %25.25s\n","Reader:", getSpeaker()));
        sb.append(String.format("%-20s %25.25s\n","Duration:", getDuration()));
        sb.append(String.format("%-20s %25d\n","Number of views:", getViews()));
        sb.append(String.format("%-20s %25.1f\n","Book rate:", getRate()));
        sb.append(String.format("%-20s %25.25s\n","Book cover URL:", getCover()));
        sb.append(String.format("%-20s %25.25s\n","Book file URL:", getContent()));

    return sb.toString();
    }
    public boolean isValid(){
        return  this.getName()!=null &&
                this.getGenre()!=null &&
                this.getAuthor()!=null &&
                this.getSpeaker()!=null &&
                this.getDuration()!=null &&
                this.getDescription()!=null &&
                this.getCover()!=null &&
                this.getContent()!=null;
    }
}

