package com.Sofka.service;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sofka.domain.Users;
import com.Sofka.repository.UsersRepository;
import com.Sofka.service.Interface.Iusers;

@Service
public class UserService  implements Iusers {
	
	@Autowired
	private UsersRepository UsersRepository;
	
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
	public Users updateUser(Integer id, Users users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users updateUsername(Integer id, Users users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users updatePassword(Integer id, Users users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Users usuario) {
		// TODO Auto-generated method stub
		
	}
}
