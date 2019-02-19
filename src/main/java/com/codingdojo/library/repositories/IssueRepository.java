package com.codingdojo.library.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.library.models.Issue;
import com.codingdojo.library.models.Issue.IssueId;

@Repository
public interface IssueRepository extends CrudRepository<Issue, IssueId>{
	//get all issued books
	List<Issue> findAll();
	
	//get issue date
//	Date getIssuedate();
}
