package com.example.book.model.entity;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "Quotations", foreignKeys = {
        @ForeignKey(
                entity =BookEntity.class,
                parentColumns = "Id",
                childColumns = "BookId",
                onDelete = CASCADE
        ),
})
public class QuotationEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id", typeAffinity = ColumnInfo.INTEGER)
    private int id;

    @ColumnInfo(name = "Name", typeAffinity = ColumnInfo.TEXT)
    private String name;

    @ColumnInfo(name = "Image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "Page", typeAffinity = ColumnInfo.INTEGER)
    private int page;

    @ColumnInfo(name = "Content", typeAffinity = ColumnInfo.TEXT)
    private String content;

    @ColumnInfo(name = "BookId", typeAffinity = ColumnInfo.INTEGER)
    private int bookId;

    public QuotationEntity() {
    }

    public QuotationEntity(String name, byte[] image, int page, String content, int bookId) {
        this.name = name;
        this.image = image;
        this.page = page;
        this.content = content;
        this.bookId = bookId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}