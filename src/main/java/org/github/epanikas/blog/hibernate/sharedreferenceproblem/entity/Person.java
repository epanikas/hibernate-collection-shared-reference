package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.Setter;
import org.github.epanikas.blog.typedid.PersonId;
import org.github.epanikas.blog.typedid.usertype.LongIdUserTypes;
import org.github.epanikas.blog.typedid.usertype.SequenceGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.util.List;

import static org.hibernate.id.enhanced.SequenceStyleGenerator.SEQUENCE_PARAM;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
@Getter
@Setter
public class Person<IdType extends PersonId<IdType>> {

    @Id
    @Column(name = "ID")
    @Type(LongIdUserTypes.PersonIdUserType.class)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @GenericGenerator(name = "idgen",
            type = SequenceGenerators.PersonIdSequenceGenerator.class,
            parameters = {
                    @Parameter(name = SEQUENCE_PARAM, value = "MY_SCHEMA.MY_ID_SEQUENCE"),
                    @Parameter(name = "increment_size", value = "50")
            }
    )
    private PersonId<IdType> personId;

    protected PersonId<IdType> getPersonId() {
        return personId;
    }

    public IdType getId() {
        return this.getIdInternal();
    }

    @SuppressWarnings("unchecked")
    protected IdType getIdInternal() {
        return (IdType) getPersonId();
    }

    private String name;

    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Book> books;

    public Person() {
        //
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
