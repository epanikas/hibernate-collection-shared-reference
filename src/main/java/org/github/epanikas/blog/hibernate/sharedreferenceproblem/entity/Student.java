package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends Person {

    private String major;
    private String minor;

}
