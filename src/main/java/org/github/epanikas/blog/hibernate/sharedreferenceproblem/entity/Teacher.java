package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    private String labName;

}
