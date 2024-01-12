package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.github.epanikas.blog.typedid.StudentId;
import org.github.epanikas.blog.typedid.TeacherId;

@Entity
@Table(name = "teachers")
public class Teacher extends Person<TeacherId> {

    private transient TeacherId teacherId;

    @Override
    protected TeacherId getIdInternal() {
        if (teacherId == null && getPersonId() != null) {
            teacherId = TeacherId.of(getPersonId().asLong());
        }
        return teacherId;
    }



    private String labName;

}
