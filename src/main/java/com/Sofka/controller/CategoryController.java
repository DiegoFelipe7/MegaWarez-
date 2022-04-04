package com.Sofka.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Sofka.domain.Category;
import com.Sofka.service.CategoryService;
import com.Sofka.utility.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CategoryController {
	/**
     * Variable para el manejo de las respuestas de las API
     */
	private Response response = new Response();
	/**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;
    
    /**
     * Repositorio de usuarios
     */
    @Autowired
    private CategoryService categoryService;
    
    /**
     * Index del sistema, responde con el listado de usuarios 
     *
     * @return Objeto Response en formato JSON
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/categoria")
    public ResponseEntity<Response> getCategoria() {
        response.restart();
        try {
            response.data = categoryService.getCategoria();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
           getErrorMessageInternal(exception);
        }
        return new ResponseEntity<Response>(response, httpStatus);
    }
    /**
     * Crea un nueva categoria  en el sistema
     *
     * @param categoria Objeto categoria a crear
     * @return Objeto Response en formato JSON
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
    
	  @PostMapping(path = "/api/categoria")
	    public ResponseEntity<Response> crearCategoria(@RequestBody Category categoria) {
	        response.restart();
	        try {
	            //log.info("Contacto a crear: {}", contacto);
	            response.data = categoryService.saveCategory(categoria);
	            httpStatus = HttpStatus.CREATED;
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity<Response>(response, httpStatus);
	    }
	  
	  /**
	     * elimina  una  categoria  en el sistema
	     *
	     * @param id Int id categoria a eliminar
	     *
	     * @author Diego Muñoz <diegofelipem99@gmail.com>
	     * @since 1.0.0
	     */
	  
	  
	  @DeleteMapping(path = "/api/categoria/{id}")
	    public ResponseEntity<Response> deleteCategoria(@PathVariable(value="id") Integer id) {
	        response.restart();
	        try {
	            response.data = categoryService.deleteCategoria(id);
	            if (response.data == null) {
	                response.message = "La categoria no existe";
	                httpStatus = HttpStatus.NOT_FOUND;
	            } else {
	                response.message = "La categoria fue removido exitosamente";
	                httpStatus = HttpStatus.OK;
	            }
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity(response, httpStatus);
	    }
	  
	  
	    /**
	     * Actualiza el status de una categoria
	     *
	     * @param categoria Objeto Categoria
	     * @param id Identificador del a actualizar
	     * @return Objeto Response en formato JSON
	     *
	     * @author Diego Muñoz <diegofelipem99@gmail.com>
	     * @since 1.0.0
	     */
	    @PatchMapping(path = "/api/categoria/status/{id}")
	    public ResponseEntity<Response> updateStatus(
	            @RequestBody Category categoria,
	            @PathVariable(value="id") Integer id
	    ) {
	        response.restart();
	        try {
	        	response.data = categoryService.deleteLogic(id, categoria);
	        	 if (response.data == null) {
		                response.message = "La categoria no existe";
		                httpStatus = HttpStatus.NOT_FOUND;
		            } else {
		                response.message = "La categoria fue removido exitosamente";
		                httpStatus = HttpStatus.OK;
		            }
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity(response, httpStatus);
	    }
	    /**
	     * Devuelve el listado de categorias por su nombre
	     *
	     * @param dataToSearch Información a buscar
	     * @return Objeto Response en formato JSON
	     *
	     * @author Diego Muñoz <diegofelipem99@gmail.com>
	     * @since 1.0.0
	     */
	    @GetMapping(path = "/api/search/categoria/{dataToSearch}/{order}")
	    public ResponseEntity<Response> searchContactByNombreOrApellido(
	            @PathVariable(value="dataToSearch") String dataToSearch
	    ) {
	        response.restart();
	        try {
	            response.data = categoryService.searchCategory(dataToSearch);
	            httpStatus = HttpStatus.OK;
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity(response, httpStatus);
	    }
	    

	  /**
	     * Administrador para las excepciones del sistema
	     *
	     * @param exception Objeto Exception
	     *
	     * @author Julian Lasso <julian.lasso@sofka.com.co>
	     * @since 1.0.0
	     */
	    private void getErrorMessageInternal(Exception exception) {
	        response.error = true;
	        response.message = exception.getMessage();
	        response.data = exception.getCause();
	        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    
	  /**
	     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
	     *
	     * @param exception Objeto DataAccessException
	     *
	     * @author Julian Lasso <julian.lasso@sofka.com.co>
	     * @since 1.0.0
	     */
	    private void getErrorMessageForResponse(DataAccessException exception) {
	        response.error = true;
	        if(exception.getRootCause() instanceof SQLException) {
	            SQLException sqlEx = (SQLException) exception.getRootCause();
	            var sqlErrorCode = sqlEx.getErrorCode();
	            switch (sqlErrorCode) {
	                case 1062:
	                    response.message = "El dato ya está registrado";
	                    break;
	                case 1452:
	                    response.message = "El usuario indicado no existe";
	                    break;
	                default:
	                    response.message = exception.getMessage();
	                    response.data = exception.getCause();
	            }
	            httpStatus = HttpStatus.BAD_REQUEST;
	        } else {
	            response.message = exception.getMessage();
	            response.data = exception.getCause();
	            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	    }
    
}
