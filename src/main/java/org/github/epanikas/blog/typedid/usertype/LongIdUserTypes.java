package org.github.epanikas.blog.typedid.usertype;

import org.github.epanikas.blog.typedid.PersonId;
import org.hibernate.HibernateException;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.BasicJavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.NumericJdbcType;
import org.hibernate.usertype.BaseUserTypeSupport;
import org.hibernate.usertype.EnhancedUserType;

import java.math.BigDecimal;
import java.util.function.BiConsumer;

public class LongIdUserTypes {

    private LongIdUserTypes() {
        //
    }

    public static class PersonIdUserType extends BaseUserTypeSupport<PersonId> implements EnhancedUserType<PersonId> {

        @Override
        protected void resolve(BiConsumer<BasicJavaType<PersonId>, JdbcType> resolutionConsumer) {
            resolutionConsumer.accept(descriptor, NumericJdbcType.INSTANCE);
        }

        @Override
        public String toSqlLiteral(PersonId value) {
            return Long.toString(value.asLong());
        }

        @Override
        public String toString(PersonId value) {
            return Long.toString(value.asLong());
        }

        @Override
        public PersonId fromStringValue(CharSequence sequence) throws HibernateException {
            return PersonIdMaterializer.ofString(sequence.toString());
        }

        private final BasicJavaType<PersonId> descriptor = new BasicJavaType<PersonId>() {
            @Override
            public <X> X unwrap(PersonId value, Class<X> type, WrapperOptions options) {
                return (X) BigDecimal.valueOf(value.asLong());
            }

            @Override
            public <X> PersonId wrap(X value, WrapperOptions options) {
                return PersonIdMaterializer.ofLong(((BigDecimal)value).longValue());
            }

            @Override
            public Class<PersonId> getJavaTypeClass() {
                return PersonId.class;
            }
        };

    }
}
