package org.github.epanikas.blog.typedid;

public class StudentId extends PersonId<StudentId> {

    private StudentId(long id) {
        super(id);
    }

    public static StudentId of(Long id) {
        return id == null ? null : new StudentId(id);
    }
}
