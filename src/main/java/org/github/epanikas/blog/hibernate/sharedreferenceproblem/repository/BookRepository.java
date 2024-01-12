package org.github.epanikas.blog.hibernate.sharedreferenceproblem.repository;

import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Book;
import org.github.epanikas.blog.hibernate.sharedreferenceproblem.entity.Student;
import org.github.epanikas.blog.typedid.StudentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}