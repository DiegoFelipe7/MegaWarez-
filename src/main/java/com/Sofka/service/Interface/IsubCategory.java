package com.Sofka.service.Interface;

import java.util.List;

import com.Sofka.domain.Category;
import com.Sofka.domain.SubCategory;

public interface IsubCategory {
	/**
     * Devuelve una lista de subcategoria 
     *
     * @return
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public List<SubCategory> getSubCategory();
	 /**
     * Crea un subcategoria en el sistema
     *
     * @param subcategoria Objeto del la subcategoria a crear
     * @return Objeto del categoria creado
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public SubCategory saveSubCategory(SubCategory subcategory);
	
	 /**
     * actualiza los datos de una subcategoria en el sistema
     *
     * @param subcategoria Objeto del subcategoria a actualizar
     * @param id Objeto del subcategoria a actualizar
     * 
     * @return Objeto del subcategoria actualizado
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public SubCategory updateSubcategory(Integer id , SubCategory subCategory);
	
	
	 /**
     * elimina una subcategoria del sistema 
     * @param id Integer
     * 
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	
	public SubCategory deleteCategoria (Integer id);
	
	
	
	
}
