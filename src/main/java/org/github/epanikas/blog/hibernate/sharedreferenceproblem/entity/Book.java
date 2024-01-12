package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.github.epanikas.blog.typedid.PersonId;

@Entity
@Table(name = "books")
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @SequenceGenerator(name = "idgen", sequenceName = "MY_ID_SEQUENCE")
    private long id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Person<? extends PersonId<?>> owner;

    private String title;

    private String author;

    public Book() {
        //
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }


}
