package org.github.epanikas.blog.typedid.usertype;

import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.github.epanikas.blog.typedid.LongId;
import org.github.epanikas.blog.typedid.PersonId;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.LongJavaType;
import org.hibernate.type.descriptor.jdbc.NumericJdbcType;
import org.hibernate.type.internal.BasicTypeImpl;

import java.util.Properties;
import java.util.function.Function;

public class SequenceGenerators {

    private SequenceGenerators() {
        //
    }

    @RequiredArgsConstructor
    public static class LongIdBasedSequenceGenerator<T extends LongId<?>> extends SequenceStyleGenerator {

        private final Function<Long, T> creator;

        @Override
        public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) throws MappingException {
            super.configure(new BasicTypeImpl<>(LongJavaType.INSTANCE, NumericJdbcType.INSTANCE), parameters, serviceRegistry);
        }

        @Override
        public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
            return creator.apply((Long) super.generate(session, object));
        }
    }

    public static class PersonIdSequenceGenerator extends LongIdBasedSequenceGenerator<PersonId<?>> {
        public PersonIdSequenceGenerator() {
            super(PersonIdMaterializer::ofLong);
        }
    }
}
