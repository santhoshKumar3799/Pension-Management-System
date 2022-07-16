package com.pms.pensionerdetail.PensionerDetailsService.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.pensionerdetail.PensionerDetailsService.model.BankLog;



/**
 * This repository interface is used for accessing bank log table
 */
@Repository
@Transactional
public interface BankRepository extends JpaRepository<BankLog, Long>{

}
