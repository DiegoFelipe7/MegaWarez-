package com.Sofka.repository;

import java.time.Instant;
import java.util.List;

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

	 /*
	 * Actualiza el nombre de un usuario basado en su identificador
	 *
	 * @param id Identificador del usuarrio
	 * @param nombre Nuevo nombre del usuario
	 *
	 * @author Diego Felipe Muñoz <diegofelipem99@gmail.com>
	 * @since 1.0.0
	*/

   @Modifying
    @Query("update Users user set user.username = :username  where user.id = :id")
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
  	 */
  	  @Modifying
      @Query("update Users user set user.password = :password where user.id = :id")
      public void updatePassword(
              @Param(value = "id") Integer id,
              @Param(value = "password") String password
      );

  	  @Modifying
      @Query("SELECT user FROM  Users user where  user.username = :username and  user.password = :password")
      public Users login_user(
              @Param(value = "username") String username,
              @Param(value = "password") String password
      );
  	  
  	  
  	 @Modifying
     @Query("SELECT user FROM  Users user where  user.username = :username and  user.password = :password")
     public List<Users> login_user2(
             @Param(value = "username") String username,
             @Param(value = "password") String password
     );

  	
}
