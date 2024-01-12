package org.github.epanikas.blog.typedid;

public abstract class PersonId<IdType extends LongId<IdType>> extends LongId<IdType> {

    protected PersonId(long id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else {
            return PersonId.class.isAssignableFrom(o.getClass()) && this.id == ((PersonId<?>) o).id;
        }
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }
}
