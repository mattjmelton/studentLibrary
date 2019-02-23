package com.codingdojo.library.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="issued")
public class Issue {
	
	@SuppressWarnings("serial")
	@Embeddable
	public static class IssueId implements Serializable {
		@Column(name = "libid")
		protected Long libid;
		
		@Column(name = "code")
		protected Long code;
		
		public IssueId() {
		}
		public IssueId(Long libid, Long code) {
			this.libid = libid;
			this.code = code;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((libid == null) ? 0 : libid.hashCode());
			result = prime * result + ((code == null) ? 0 : code.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if(this == obj)
				return true;
			if(obj == null)
				return false;
			if(getClass() != obj.getClass())
				return false;
			
			IssueId other = (IssueId) obj;
			
			if(libid == null) {
				if(other.libid != null)
					return false;
			} else if (!libid.equals(other.libid))
				return false;
			
			if(code == null) {
				if(other.code != null)
					return false;
			}else if (!code.equals(other.code))
				return false;
			
			return true;
		}
	}
	//attributes
	@EmbeddedId
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private IssueId id;
	@Column(name = "check_out")
	private LocalDate issuedate;
	@Column(name = "due_date")
	private LocalDate returndate;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToOne
//	@JoinColumn(name="libid", insertable=false, updatable=false)
	@MapsId("libid")
	private Student student;
	
	@ManyToOne
//	@JoinColumn(name="code", insertable=false, updatable=false)
	@MapsId("code")
	private Book book;
	
	//constructor
	public Issue() {
		
	}
	public Issue(Student s, Book b, LocalDate checkout, LocalDate returndate) {
		//create primary key
		this.id = new IssueId(s.getLibID(), b.getCode());
		//initialize attributes
		this.student = s;
		this.book = b;
		this.issuedate = checkout;
		this.returndate = returndate;
		Date now = new Date();
		this.createdAt = now;
		//update relationships to ensure referential integrity
		b.getIssuedBooks().add(this);
		s.getIssuedToStudent().add(this);
	}
	
	
	
	//getters and setters
	public IssueId getId() {
		return id;
	}

	public void setId(IssueId id) {
		this.id = id;
	}

	public LocalDate getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(LocalDate issuedate) {
		this.issuedate = issuedate;
	}

	public LocalDate getReturndate() {
		return returndate;
	}

	public void setReturndate(LocalDate returndate) {
		this.returndate = returndate;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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
