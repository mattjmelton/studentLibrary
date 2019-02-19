package com.codingdojo.library.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.library.models.Book;
import com.codingdojo.library.models.Student;
import com.codingdojo.library.models.Issue;
import com.codingdojo.library.repositories.BookRepository;
import com.codingdojo.library.repositories.IssueRepository;
import com.codingdojo.library.repositories.StudentRepository;

@Service
public class LibraryService {
	private final BookRepository bookRepo;
	private final StudentRepository studentRepo;
	private final IssueRepository issueRepo;
	
	public LibraryService(BookRepository bookRepo, 
			StudentRepository studentRepo,
			IssueRepository issueRepo) {
		this.bookRepo = bookRepo;
		this.studentRepo = studentRepo;
		this.issueRepo = issueRepo;
	}
	// returns all the books
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    // get all students
    public List<Student> allStudents(){
    	return studentRepo.findAll();
    }
  //Registration and Login methods for Student
  	//register the Student and hash their password
  	public Student registerUser(Student user) {
  			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
  			user.setPassword(hashed);
  			return studentRepo.save(user);
  	}
  		//find user by email
  	public Student findByEmail(String email) {
  			return studentRepo.findByEmail(email);
  	}
  		//find user by id
  	public Student findUserById(Long id) {
  		Optional<Student> u = studentRepo.findById(id);
  		if(u.isPresent()) {
  			return u.get();
  		}else {
  			return null;
  		}
  	}
  	//authenticate user
  	public boolean authenticateUser(String email, String password) {
  			//first find the user by email
  		Student user = studentRepo.findByEmail(email);
  			//if can't find email, return false
  		if(user == null) {
  				return false;
  		}else {
  				//if the passwords match, return true, else, return false
  			if(BCrypt.checkpw(password, user.getPassword())) {
  				return true;
  			}else {
  				return false;
  			}
  		}
  	}
    
    // get one student
    public Student findStudent(Student id) {
    	return studentRepo.save(id);
    }
    //get student by id
    public Student findStudentById(Long id) {
    	Optional<Student> optionalStudent = studentRepo.findById(id);
        if(optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            return null;
        }
    }
    //issue a book to a student
    public void issueBook(Student student, Book book) {
    	Integer numC = book.getNumberOfCopies();
    	if(numC == 0) {
    		return;
    	}
    	else {
    		//add record for student issued a book
//    		student.getBooks().add(book);
//    		book.getIssuedBooks().add(book);
//    		student.getIssuedToStudent().add(book);
//    		studentRepo.save(student);
    		
    		LocalDate checkout = LocalDate.now();
    		LocalDate returndate = checkout.plusDays(14);
    		System.out.println(checkout + " " + returndate);
    		Issue issue = new Issue(student, book, checkout, returndate);
    		issueRepo.save(issue);
    		decreaseCopies(book);
    	}
    }
    
    //decrease number of copies when checked out
    public void decreaseCopies(Book book) {
    	Integer checkout = book.getNumberOfCopies();
    	checkout--;
    	book.setNumberOfCopies(checkout);
    	bookRepo.save(book);
    }
  //increase number of copies when returned
    public void increaseCopies(Book book) {
    	Integer checkout = book.getNumberOfCopies();
    	checkout++;
    	book.setNumberOfCopies(checkout);
    	bookRepo.save(book);
    }
    // get book issued to a student
    public List<Issue> getAllIssuedBooks(Student student){
    	return student.getIssuedToStudent();
    }
    // delete issue record and increase number of copies
//    public void returnBook(Book book) {
//    	Long bookid = book.getCode();
//    	
//    	issueRepo.delete();
//    	increaseCopies(book);
//    }
    
    // create student
    public Student createStudent(Student s) {
    	return studentRepo.save(s);
    }
    
    // creates a book
    public Book createBook(Book b) {
        return bookRepo.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    //updates a book
//    public Book updateBook(Book b) {
//        return bookRepo.save(b);
//    }

    //delete a book
    public Book deleteBook(Long id) {
    	bookRepo.deleteById(id);
    	return null;
    }
}
