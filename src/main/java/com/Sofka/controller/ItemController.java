package com.Sofka.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Sofka.domain.Item;
import com.Sofka.domain.SubCategory;
import com.Sofka.service.ItemService;
import com.Sofka.service.SubCategoryService;
import com.Sofka.utility.Response;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class ItemController {
	/**
     * Variable para el manejo de las respuestas de las API
     */
	@Autowired
	private ItemService itemService;
	
	/**
    * Manejo del código HTTP que se responde en las API
    */
   private HttpStatus httpStatus = HttpStatus.OK;
	
	private Response response = new Response();
	

	@GetMapping("api/item")
	public ResponseEntity<Response> getItem(){
		response.restart();
		try {
			response.data=itemService.getItem();
			httpStatus = HttpStatus.OK;
			
		} catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
	}
	/**
     * Devuelve las sub categorias ordenados en forma ascendente o descendente
     *
     * @param orderBy Nombre del campo por donde se desea ordenar la información
     * @param order Tipo de orden que debe tener la información ASC o DESC
     * @return Objeto Response en formato JSON
     *
     * @author Diego felipe muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
    /*@GetMapping(path = "/api/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> indexOrderBy(
            @PathVariable(value="orderBy") String orderBy,
            @PathVariable(value="order") Sort.Direction order
    ) {
        response.restart();
        try {
            response.data = subcategoryservice.getListSubcategory(orderBy, order);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }*/
	@PostMapping("api/item")
	public ResponseEntity<Response> saveSubcategory(@RequestBody Item item){
		response.restart();
		try {
			response.data=itemService.saveItem(item);
			 httpStatus = HttpStatus.CREATED;
		} catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<Response>(response, httpStatus);
    }
	@PutMapping("api/item/{id}")
	public ResponseEntity<Response> updateSubCategoria(@PathVariable(value="id") Integer id , @RequestBody Item item){
		response.restart();
		try {
			response.data=itemService.updateItem(id, item);
			 httpStatus = HttpStatus.OK;
		} catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
	}
	
	@DeleteMapping("api/item/{id}")
	public ResponseEntity<Response> deleteSubCategor(@PathVariable(value="id") Integer id){
		response.restart();
		try {
			response.data=itemService.deleteItem(id);
			 if (response.data == null) {
	                response.message = "La sub categoria no existe";
	                httpStatus = HttpStatus.NOT_FOUND;
	         } else {
	                response.message = "La sub categoria fue removido exitosamente";
	                httpStatus = HttpStatus.OK;
	         }
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@PatchMapping("api/item/status/{id}")
	public ResponseEntity<Response> deleteLogicSub ( @RequestBody Item item, @PathVariable(value="id") Integer id){
		 response.restart();
	        try {
	        	response.data = itemService.deleteLogicoItem(id, item);
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
