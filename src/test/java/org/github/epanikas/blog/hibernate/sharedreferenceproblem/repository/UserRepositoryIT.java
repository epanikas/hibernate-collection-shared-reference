package org.github.epanikas.blog.hibernate.sharedreferenceproblem.repository;

import org.apache.log4j.Logger;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.datasource.DataSourceConfig;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class UserRepositoryIT {

    private static final Logger log = Logger.getLogger(UserRepositoryIT.class);

    @Autowired
    private PersonRepository userRepository;

    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        Person saved = userRepository.save(new Person("Bob", "bob@domain.com"));

        log.info(saved);

        List<Person> users = (List<Person>) userRepository.findAll();

        assertThat(users.size()).isEqualTo(2);
    }

//    @Before
//    public void setUp() {
//        StreamSupport.stream(userRepository.findAll().spliterator(), false)
//                .filter(u -> "Bob".equals(u.getName()))
//                .forEach(u -> userRepository.deleteById(u.getId()));
//    }
}