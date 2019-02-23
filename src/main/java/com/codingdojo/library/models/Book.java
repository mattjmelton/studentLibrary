package com.codingdojo.library.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long code;
	 
	 @Size(min = 5, max = 255)
	 private String bookname;
	 
	 @Size(min = 3, max = 255)
	 private String author;
	 private String subject;
	 private String publication;
	 
	 @Min(0)
	 private Integer numberOfCopies;
	 
	 // This will not allow the createdAt column to be updated after creation
	 @Column(updatable=false)
	 private Date createdAt;
	 private Date updatedAt;
	 
//	 @ManyToMany(fetch = FetchType.LAZY)
//	 @JoinTable(
//			 name = "issued",
//			 joinColumns = @JoinColumn(name = "code"),
//			 inverseJoinColumns = @JoinColumn(name = "libid")
//			 )
//	 private List<Student> students;
	 @OneToMany(mappedBy = "book")
	 private List<Issue> issuedBooks;
	 
	 public Book() {
	 }
//	 public Book(String title, String desc, String lang, int pages) {
//	     this.title = title;
//	     this.description = desc;
//	     this.language = lang;
//	     this.numberOfPages = pages;
//	 } 
	//getters and setters
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public Integer getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(Integer numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
//	public List<Student> getStudents() {
//		return students;
//	}
//
//	public void setStudents(List<Student> students) {
//		this.students = students;
//	}
	
	public List<Issue> getIssuedBooks() {
		return issuedBooks;
	}

	public void setIssuedBooks(List<Issue> issuedBooks) {
		this.issuedBooks = issuedBooks;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@PrePersist
	protected void onCreate(){
       this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
       this.updatedAt = new Date();
	}
}
