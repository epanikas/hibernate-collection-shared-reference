package org.github.epanikas.blog.hibernate.sharedreferenceproblem.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.datasource.DataSourceConfig;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Book;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Person;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Student;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Teacher;
import org.github.epanikas.blog.typedid.PersonId;
import org.github.epanikas.blog.typedid.StudentId;
import org.github.epanikas.blog.typedid.TeacherId;
import org.github.epanikas.blog.typedid.usertype.PersonIdMaterializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class UserRepositoryIT {

    private static final Logger log = Logger.getLogger(UserRepositoryIT.class);

    @Autowired
    private PersonRepository userRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager entityManager;

    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        Student person = new Student("Bob", "bob@domain.com");
        Book book = new Book("title1", "author1");
        book.setOwner(person);
        person.setBooks(Arrays.asList(book));

        Person<? extends PersonId<?>> saved = userRepository.save(person);

        log.info(saved);

//        List<Person> users = (List<Person>) userRepository.findAll();
//
//        assertThat(users.size()).isEqualTo(2);

        new TransactionTemplate(platformTransactionManager).execute((TransactionStatus status) -> {
//            Person<? extends PersonId<?>> p1 = userRepository.findById(PersonIdMaterializer.ofLong(saved.getId().asLong())).orElseThrow();
            Person<? extends PersonId<?>> p1 = userRepository.findById(StudentId.of(saved.getId().asLong())).orElseThrow();
            assertThat(p1.getBooks()).hasSize(1);

            entityManager.clear();
            entityManager.flush();

            Student p2 = (Student) userRepository.findById(TeacherId.of(saved.getId().asLong())).orElseThrow();

            assertThat(p2.getBooks()).hasSize(1);

            return null;
        });


    }

//    @Before
//    public void setUp() {
//        StreamSupport.stream(userRepository.findAll().spliterator(), false)
//                .filter(u -> "Bob".equals(u.getName()))
//                .forEach(u -> userRepository.deleteById(u.getId()));
//    }
}