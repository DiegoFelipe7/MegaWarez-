package com.Sofka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sofka.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	  /**
	 * Actualiza el nombre de un usuario basado en su identificador
	 *
	 * @param id Identificador del usuarrio
	 * @param nombre Nuevo nombre del usuario
	 *
	 * @author Diego Felipe Muñoz <diegofelipem99@gmail.com>
	 * @since 1.0.0	
	 */
	  @Modifying
	  @Query("Update Category categ set categ.status = :status where categ.id = :id")
	  public void deletelogic(
	          @Param(value = "id") Integer id,
	          @Param(value = "status") String status
	  );
	  
	  @Modifying
	  @Query( "SELECT cat FROM Category cat WHERE (cat.nombre LIKE :data%) ORDER BY cat.nombre ASC")
	  public List<Category> findbyName(@Param("data") String data);
	  
	  
	  @Modifying
	  @Query( "SELECT cat FROM Category cat WHERE cat.status=1")
	  public List<Category> finall();
}
