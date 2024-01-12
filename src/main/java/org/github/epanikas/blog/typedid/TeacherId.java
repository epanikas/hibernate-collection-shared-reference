package org.github.epanikas.blog.typedid;

public class TeacherId extends PersonId<TeacherId> {

    private TeacherId(long id) {
        super(id);
    }

    public static TeacherId of(Long id) {
        return id == null ? null : new TeacherId(id);
    }
}
