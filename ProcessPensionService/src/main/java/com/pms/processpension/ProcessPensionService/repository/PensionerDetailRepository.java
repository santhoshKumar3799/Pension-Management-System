package com.pms.processpension.ProcessPensionService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pms.processpension.ProcessPensionService.model.PensionerDetail;


@Repository
public interface PensionerDetailRepository extends JpaRepository<PensionerDetail, String>{

}
