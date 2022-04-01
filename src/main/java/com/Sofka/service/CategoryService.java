package com.Sofka.service;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sofka.domain.Category;
import com.Sofka.repository.CategoryRepository;
import com.Sofka.service.Interface.Icategoria;

@Service
public class CategoryService implements Icategoria{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getCategoria() {
		return categoryRepository.findAll();
	}

	@Override
	public Category saveCategory(Category categoria) {
		categoria.setCreatedAt(Instant.now());
		return categoryRepository.save(categoria);
	}

	@Override
	@Transactional
	public Category deleteCategoria(Integer id) {
		var categoria = categoryRepository.findById(id);
		if(categoria.isPresent()) {
			categoryRepository.delete(categoria.get());
			return categoria.get();
		}else {
			return null;
		}
	
	}
	
	@Transactional
	public void deleteLogic(Integer id , Category categoria) {
	    categoryRepository.deletelogic(id, categoria.getStatus());
	}

}
