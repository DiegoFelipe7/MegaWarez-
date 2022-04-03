package com.Sofka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sofka.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	@Modifying
	@Query("SELECT itm , sub FROM Item itm INNER JOIN SubCategory sub ON itm.id_subcategoria=sub.id  ")
	public List<Item> findall();

	@Modifying
	@Query("UPDATE Item itm set itm.status = :status where itm.id=:id")
	public void deleteLogic(
			@Param(value = "id") Integer id,
	        @Param(value = "status") String status);
}
