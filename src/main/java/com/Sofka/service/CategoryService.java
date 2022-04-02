package com.Sofka.service;

import java.time.Instant;
import java.util.HashSet;
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
		return categoryRepository.finall();
	}

	@Override
	public Category saveCategory(Category categoria) {
		categoria.setCreatedAt(Instant.now());
		categoria.setStatus("1");
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
	public Category deleteLogic(Integer id , Category categoria) {
		var categorias = categoryRepository.findById(id);
		if(categorias.isPresent()) {
			categoryRepository.deletelogic(id, categoria.getStatus());
			return categorias.get();
		}else {
			return null;
		}
	}
	
	@Transactional
	public List<Category> searchCategory(String dato){
		var search=categoryRepository.findbyName(dato);
		var answer = new HashSet<Category>();
        answer.addAll(search);
        return answer.stream().toList();
		
	}

}
