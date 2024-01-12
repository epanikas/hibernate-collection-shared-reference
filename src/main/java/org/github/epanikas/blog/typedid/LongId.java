package org.github.epanikas.blog.typedid;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public abstract class LongId<T extends LongId<T>> implements Comparable<T>, Serializable {

    protected final long id;
    private final int hashCode;

    protected LongId(long id) {
        this.id = id;
        this.hashCode = new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public int compareTo(T o) {
        return Long.compare(id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LongId<?> longId = (LongId<?>) o;

        return new EqualsBuilder().append(id, longId.id).isEquals();
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    public Long asLong() {
        return id;
    }
}
