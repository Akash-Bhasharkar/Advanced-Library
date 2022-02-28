package com.myapplication.advancedlibrary;

public class Books {

    public String title, author, page_count, image_link, more;

    public String getMore() {
        return more;
    }

    public Books(String title, String author, String page_count, String image_link, String more){
        this.title = title;
        this.author = author;
        this.page_count = page_count;
        this.image_link = image_link;
        this.more = more;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPage_count() {
        return page_count;
    }

    public String getImage_link() {
        return image_link;
    }
}
