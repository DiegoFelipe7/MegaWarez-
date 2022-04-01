package com.Sofka.repository;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Sofka.domain.Users;

/**
 * Repositorio para la entidad usuarios
 *
 * @version 1.0.0 2022-04-01
 * @author Diego Felipe Muñoz <diegofelipem99@gmail.com>
 * @since 1.0.0
 */
public interface UsersRepository extends JpaRepository<Users , Integer> {

	  /**
	 * Actualiza el nombre de un usuario basado en su identificador
	 *
	 * @param id Identificador del usuarrio
	 * @param nombre Nuevo nombre del usuario
	 *
	 * @author Diego Felipe Muñoz <diegofelipem99@gmail.com>
	 * @since 1.0.0
	

   @Modifying
    @Query("update usuario user set user.usu_username = :username , usur.usu_create_at=CURRENT_TIMESTAMP where user.usu_id = :id")
    public void updateUsername(
            @Param(value = "id") Integer id,
            @Param(value = "username") String username
    );
	
    /**
  	 * Actualiza el password de un usuario basado en su identificador
  	 *
  	 * @param id Identificador del usuarrio
  	 * @param nombre Nuevo nombre del usuario
  	 *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
  	 * @since 1.0.0
  	  @Modifying
      @Query("update usuario user set user.usu_password = :password , usur.usu_update_at=CURRENT_TIMESTAMP where user.usu_id = :id")
      public void updatePassword(
              @Param(value = "id") Integer id,
              @Param(value = "password") String password
      );
	*/
	
}
