package com.bridgelabz.bookstore.bookservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String bookName;

    private String bookDescription;

    private String bookAuthor;

    private String bookLogo;

    private int bookPrice;

    private int bookQuantity;

    public BookEntity()
    {

    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookLogo='" + bookLogo + '\'' +
                ", bookPrice='" + bookPrice + '\'' +
                ", bookQuantity='" + bookQuantity + '\'' +
                '}';
    }
}
