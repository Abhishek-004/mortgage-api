package com.Mortgage.MortgageLoanAPI.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Mortgage.MortgageLoanAPI.models.Comment;

public interface ICommentDao extends CrudRepository<Comment, Long>{
	@Query("select c from Comment c where c.application_id=?1 and is_delete = 0")
	 List<Comment> findByApplicationId(Long id);
}
