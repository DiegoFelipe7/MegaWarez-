package com.Sofka.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Sofka.domain.Users;
import com.Sofka.service.UserService;
import com.Sofka.utility.LoginData;
import com.Sofka.utility.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UsersController {

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
    private UserService userservice;
	
    /**
     * Index del sistema, responde con el listado de usuarios 
     *
     * @return Objeto Response en formato JSON
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/api/users")
    public ResponseEntity<Response> getUsuarios() {
        response.restart();
        try {
            response.data = userservice.getListUsers();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
           getErrorMessageInternal(exception);
        }
        return new ResponseEntity<Response>(response, httpStatus);
    }
    /**
     * Crea un nuevo usuario  en el sistema
     *
     * @param usuarios Objeto usuario a crear
     * @return Objeto Response en formato JSON
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
    
	  @PostMapping(path = "/api/users")
	    public ResponseEntity<Response> createUsuario(@RequestBody Users usuarios) {
	        response.restart();
	        try {
	            response.data = userservice.SaveUsers(usuarios);
	            httpStatus = HttpStatus.CREATED;
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity<Response>(response, httpStatus);
	    }
	  @PutMapping(path = "/api/users/{id}")
	    public ResponseEntity<Response> updateUser(
	            @RequestBody Users usuarios,
	            @PathVariable(value="id") Integer id
	    ) {
	        response.restart();
	        try {
	            response.data = userservice.updateUser(id, usuarios);
	            httpStatus = HttpStatus.OK;
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity(response, httpStatus);
	    }
	  

	  @DeleteMapping(path = "/api/users/{id}")
	  public ResponseEntity<Response> deleteUser(@PathVariable(value="id") Integer id){
		  response.restart();
	        try {
	            response.data = userservice.deleteUser(id);
	            if (response.data == null) {
	                response.message = "El usuario no existe";
	                httpStatus = HttpStatus.NOT_FOUND;
	            } else {
	                response.message = "El usuario fue removido exitosamente";
	                httpStatus = HttpStatus.OK;
	            }
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity(response, httpStatus);
	  }

	  
	  @PostMapping(path = "/api/login")
	    public ResponseEntity<Response> login(@RequestBody LoginData loginData) {
	        response.restart();
	        try {
	        	response.data=loginData.getToken();
	        	if (response.data == null) {
		             response.message = "Nombre de usuario o contraseña incorrecta";
		             httpStatus = HttpStatus.NOT_FOUND;
		         } else {
		                response.message = "Sesion iniciada";
		                httpStatus = HttpStatus.OK;
		         }
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity(response, httpStatus);
	    }
	  
	  
	  @PostMapping(path = "/api/login2")
	    public ResponseEntity<Response> login2(@RequestBody Users usuarios ) {
	        response.restart();
	        try {
	        	response.data=userservice.Login2(usuarios);
	        	if (response.data == null) {
		             response.message = "Nombre de usuario o contraseña incorrecta";
		             httpStatus = HttpStatus.NOT_FOUND;
		         } else {
		                response.message = "Sesion iniciada";
		                httpStatus = HttpStatus.OK;
		         }
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity(response, httpStatus);
	    }
	  
	  @GetMapping(path = "/api/v1/ejemplo-token")
	    public ResponseEntity<Response> getToken(@RequestHeader("Authorization") String authorization) {
	        response.restart();
	        try {
	            response.message = "Todo OK - TOKEN";
	            response.data = authorization.replace("Bearer ", "");
	            httpStatus = HttpStatus.OK;
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
