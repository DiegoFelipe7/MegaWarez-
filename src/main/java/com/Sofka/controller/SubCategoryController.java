package com.Sofka.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Sofka.domain.SubCategory;
import com.Sofka.service.SubCategoryService;
import com.Sofka.utility.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SubCategoryController {
	/**
     * Variable para el manejo de las respuestas de las API
     */
	@Autowired
	private SubCategoryService subcategoryservice;
	
	/**
    * Manejo del código HTTP que se responde en las API
    */
   private HttpStatus httpStatus = HttpStatus.OK;
	
	private Response response = new Response();
	
	
	@GetMapping("api/subcategoria")
	public ResponseEntity<Response> getSubcategorias(){
		response.restart();
		try {
			response.data=subcategoryservice.getSubCategory();
			httpStatus = HttpStatus.OK;
			
		} catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
	}
	
	@PostMapping("api/subcategoria")
	public ResponseEntity<Response> saveSubcategory(@RequestBody SubCategory subcategoria){
		response.restart();
		try {
			response.data=subcategoryservice.saveSubCategory(subcategoria);
			 httpStatus = HttpStatus.CREATED;
		} catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<Response>(response, httpStatus);
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
