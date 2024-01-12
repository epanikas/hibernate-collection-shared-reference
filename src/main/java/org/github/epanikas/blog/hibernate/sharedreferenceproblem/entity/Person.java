package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @SequenceGenerator(name = "idgen", sequenceName = "MY_ID_SEQUENCE")
    private long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {
        //
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
