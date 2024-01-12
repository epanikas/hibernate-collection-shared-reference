package org.github.epanikas.blog.hibernate.sharedreferenceproblem.repository;

import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Person;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Student;
import org.github.epanikas.blog.typedid.PersonId;
import org.github.epanikas.blog.typedid.StudentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, StudentId> {

}