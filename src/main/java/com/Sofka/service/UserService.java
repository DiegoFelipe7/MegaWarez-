package com.Sofka.service;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sofka.domain.Session;
import com.Sofka.domain.Users;
import com.Sofka.repository.SessionRepository;
import com.Sofka.repository.UsersRepository;
import com.Sofka.service.Interface.Iusers;

@Service
public class UserService  implements Iusers {
	
	@Autowired
	private UsersRepository UsersRepository;
	
	@Autowired
	private SessionRepository sessionrepository;
	
	@Override
    @Transactional()
	public List<Users> getListUsers() {
		return UsersRepository.findAll();
	}

	@Override
    @Transactional()
	public Users SaveUsers(Users usuarios) {
		usuarios.setCreate_at(Instant.now());
		return UsersRepository.save(usuarios);
	}

	@Override
	public Users updateUser(Integer id, Users usuarios) {
		usuarios.setId(id);
		usuarios.setUpdate_at(Instant.now());
		usuarios.setCreate_at(Instant.now());
        return UsersRepository.save(usuarios);
	}

	@Override
	public Users updateUsername(Integer id, Users users) {
		users.setId(id);
		users.setUpdate_at(Instant.now());
		UsersRepository.updateUsername(id, users.getUsername());
		return users;
				
	}

	@Override
	public Users updatePassword(Integer id, Users users) {
		users.setId(id);
		users.setUpdate_at(Instant.now());
		UsersRepository.updateUsername(id, users.getPassword());
		return users;
	}

	@Override
	public Users deleteUser(Integer id) {
		var usuario = UsersRepository.findById(id);
		if(usuario.isPresent()) {
			UsersRepository.delete(usuario.get());
			return usuario.get();
		}else {
		return null;
		}
	}
	
	
	@Override
	public Session Login(Users usuario, String token , Session sesion) {
		/*var login = UsersRepository.login_user(usuario.getUsername(), usuario.getPassword());
		if(!login.isEmpty()) {
		sesion.setCreate_at(Instant.now());
		sesion.setId_usuario(usuario);
		sesion.setToken(token);
		return sessionrepository.save(sesion);
		}else {
			return null;
		}*/
		return null;
	}
	
	public Users Login2(Users usuario) {
		return (Users) UsersRepository.login_user2(usuario.getUsername(), usuario.getPassword());
		/*if(!login.isEmpty()) {
			return (Users) login;
		}else {
			return (Users) login;
		}*/
	
	}


}
