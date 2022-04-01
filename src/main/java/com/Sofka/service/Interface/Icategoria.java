package com.Sofka.service.Interface;
import java.util.List;

import com.Sofka.domain.Category;
public interface Icategoria {
	/**
     * Devuelve una lista de categoria 
     *
     * @return
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public List<Category> getCategoria();
	 /**
     * Crea un categoria en el sistema
     *
     * @param categoria Objeto del la categoria a crear
     * @return Objeto del categoria creado
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	public Category saveCategory(Category categoria);
	 /**
     * elimina una categoria del sistema 
     * @param categoria Object
     * 
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
	
	public Category deleteCategoria (Integer id);
	
	
	
}
