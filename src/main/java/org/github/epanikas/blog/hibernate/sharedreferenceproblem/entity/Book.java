package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @SequenceGenerator(name = "idgen", sequenceName = "MY_ID_SEQUENCE")
    private long id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Person owner;

    private String title;

    private String author;



}
