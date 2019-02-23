package com.codingdojo.library.repositories;

//import java.util.Date;
import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.library.models.Issue;
import com.codingdojo.library.models.Issue.IssueId;

@Repository
public interface IssueRepository extends CrudRepository<Issue, IssueId>{
	//get all issued books
	List<Issue> findAll();
	
//	@Query("SELECT i FROM Issue i WHERE i.IssueId.code=?1 AND i.IssueId.libid=?2")
//	Issue findIssueById(Long bookid, Long studentid);
}
