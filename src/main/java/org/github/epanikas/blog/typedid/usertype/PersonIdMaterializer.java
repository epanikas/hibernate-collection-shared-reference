package org.github.epanikas.blog.typedid.usertype;

import org.github.epanikas.blog.typedid.LongId;
import org.github.epanikas.blog.typedid.PersonId;

public class PersonIdMaterializer {

    private static final class MaterializedPersonId<IdType extends LongId<IdType>> extends PersonId<IdType> {
        MaterializedPersonId(long id) {
            super(id);
        }
    }

    public static <IdType extends LongId<IdType>> PersonId<IdType> ofLong(Long id) {
        return id == null ? null : new MaterializedPersonId<>(id);
    }

    public static <IdType extends LongId<IdType>> PersonId<IdType> ofString(String id) {
        return id == null ? null : new MaterializedPersonId<>(Long.parseLong(id));
    }

}
