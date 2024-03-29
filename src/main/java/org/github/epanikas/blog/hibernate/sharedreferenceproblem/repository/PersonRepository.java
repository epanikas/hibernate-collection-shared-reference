package org.github.epanikas.blog.hibernate.sharedreferenceproblem.repository;

import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Person;
import org.github.epanikas.blog.typedid.PersonId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person<? extends PersonId<?>>, PersonId<?>> {

}