package com.Sofka.service;

import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Sort;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sofka.domain.SubCategory;
import com.Sofka.service.Interface.IsubCategory;
import com.Sofka.repository.SubCategoryRepository;

@Service
public class SubCategoryService implements IsubCategory {
	
	@Autowired
	private SubCategoryRepository subcategoryrepository; 

	@Override
	@Transactional
	public List<SubCategory> getSubCategory() {
		return subcategoryrepository.findall();
	}

	@Override
	@Transactional
	public SubCategory saveSubCategory(SubCategory subcategory) {
		subcategory.setCreate_at(Instant.now());
		subcategory.setStatus("1");
		return subcategoryrepository.save(subcategory);
	}

	@Override
	@Transactional
	public SubCategory deleteCategoria(Integer id) {
		var subcate = subcategoryrepository.findById(id);
		if(subcate.isPresent()) {
			subcategoryrepository.delete(subcate.get());
			return subcate.get();
		}else {
			return null;
		}
	}
	

	@Transactional
	public SubCategory deleteLogicSub(Integer id , SubCategory subcategory) {
		var subcate = subcategoryrepository.findById(id);
		if(subcate.isPresent()) {
			subcategoryrepository.deleteLogic(id, subcategory.getStatus());
			return subcate.get();
		}else {
			return null;
		}
	}

	@Override
	public SubCategory updateSubcategory(Integer id, SubCategory subCategory) {
		subCategory.setId(id);
		subCategory.setCreate_at(Instant.now());
		return subcategoryrepository.save(subCategory);
	}

	@Transactional
    public List<SubCategory> getListSubcategory(String field, Sort.Direction order) {
        return subcategoryrepository.findAll(Sort.by(order, field));
    }
}
