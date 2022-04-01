package com.Sofka.service.Interface;

import java.util.List;

import com.Sofka.domain.Users;

public interface Iusers {
    /**
     * Devuelve una lista de usuarios 
     *
     * @return
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public List<Users> getListUsers();
	
	 /**
     * Crea un usuario en el sistema
     *
     * @param usuarios Objeto del usuario a crear
     * @return Objeto del users creado
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public Users SaveUsers(Users usuarios);
	
	 /**
     * actualiza los datos de usuario en el sistema
     *
     * @param usuarios Objeto del usuario a actualizar
     * @param id Objeto del usuario a actualizar
     * 
     * @return Objeto del users actualizado
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public Users updateUser(Integer id ,Users users);
	 /**
     * actualiza el nombre de usuario en el sistema
     *
     * @param usuarios Objeto del usuario a actualizar
     * @param id Objeto del usuario a actualizar
     * 
     * @return Objeto del users con el nombre actualizado 
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public Users updateUsername(Integer id, Users users);
	/**
     * actualiza el password de usuario en el sistema
     *
     * @param usuarios Objeto del usuario a actualizar
     * @param id Objeto del usuario a actualizar
     * 
     * @return Objeto del users con el password actualizado
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public Users updatePassword(Integer id, Users users);
	
	 /**
     * elimina un usuario del sistema 
     * @param usuario Object
     * 
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public void deleteUser (Users usuario );
}
