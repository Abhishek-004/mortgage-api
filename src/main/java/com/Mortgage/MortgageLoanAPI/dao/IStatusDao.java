package com.Mortgage.MortgageLoanAPI.dao;

import org.springframework.data.repository.CrudRepository;

import com.Mortgage.MortgageLoanAPI.models.Status;

public interface IStatusDao extends CrudRepository<Status, Long>{

}
