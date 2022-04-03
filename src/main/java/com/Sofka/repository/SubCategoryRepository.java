package com.Sofka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sofka.domain.SubCategory;
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer>{

	@Modifying
	@Query("SELECT sub , cat FROM SubCategory sub INNER JOIN Category cat ON sub.id_categoria=cat.id  ")
	public List<SubCategory> findall();
	
	@Modifying
	@Query("UPDATE SubCategory subc set subc.status = :status where subc.id=:id")
	public void deleteLogic(
			@Param(value = "id") Integer id,
	        @Param(value = "status") Integer status);
}
