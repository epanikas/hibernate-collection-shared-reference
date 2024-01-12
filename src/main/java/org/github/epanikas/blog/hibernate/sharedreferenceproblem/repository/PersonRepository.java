package org.github.epanikas.blog.hibernate.sharedreferenceproblem.repository;

import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}