package org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.github.epanikas.blog.typedid.StudentId;

@Entity
@Table(name = "students")
public class Student extends Person<StudentId> {

    private transient StudentId studentId;

    @Override
    protected StudentId getIdInternal() {
        if (studentId == null && getPersonId() != null) {
            studentId = StudentId.of(getPersonId().asLong());
        }
        return studentId;
    }

    private String major;
    private String minor;

    public Student() {
        super();
    }

    public Student(String name, String email) {
        super(name, email);
    }

}
