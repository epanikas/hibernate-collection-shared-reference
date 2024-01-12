this project to demonstrate a hibernate problem : 

```text
    HibernateException: Found shared references to a collection: 
```

the problem is normally reported when a collection has a circular references

here is the corresponding code snippet from org.hibernate.engine.internal.Collections.java
```java

		// The CollectionEntry.isReached() stuff is just to detect any silly users
		// who set up circular or shared references between/to collections.
		if ( ce.isReached() ) {
			// We've been here before
			throw new HibernateException(
					"Found shared references to a collection: " + type.getRole()
			);
		}

```

however, as this project should demonstrate another reason why this problem can occur: multiple id's for the same entity.

Imagine we are having a class Person, and two classes inheriting from it: Student and Teacher.
Each having its own id type:

Person -> PersonId
Teacher -> TeacherId
Student -> StudentId

when the same entity is accessed via two different id's in the same transaction - then this problem will occur
it is also important, of course, to have at least one collection in the base class Person