package com.Sofka.controller;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Sofka.domain.Download;
import com.Sofka.domain.Item;
import com.Sofka.service.DownloadService;
import com.Sofka.utility.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DownloadController {
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
    private DownloadService downloadservice;
    
    @GetMapping(path = "/api/descargas")
    public ResponseEntity<Response> getDescargas(){
    	response.restart();
    	try {
    		  response.data = downloadservice.getIDownload();
              httpStatus = HttpStatus.OK;
          } catch (Exception exception) {
             getErrorMessageInternal(exception);
          }
          return new ResponseEntity<Response>(response, httpStatus);
      }
    /**
     * Crea un nueva descarga  en el sistema
     *
     * @param descarga Objeto descarga a crear
     * @return Objeto Response en formato JSON
     *
     * @author Diego Muñoz <diegofelipem99@gmail.com>
     * @since 1.0.0
     */
    
	  @PostMapping(path = "/api/descargas")
	    public ResponseEntity<Response> CrearDescarga(@RequestBody Download descarga) {
	        response.restart();
	        try {
	            //log.info("Contacto a crear: {}", contacto);
	            response.data = downloadservice.savedownload(descarga);
	            httpStatus = HttpStatus.CREATED;
	        } catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity<Response>(response, httpStatus);
	    }
	  
	  @PutMapping("/api/descarga/{id}")
	  public ResponseEntity<Response> updateDescarga(@PathVariable(value="id") Integer id , @RequestBody Download descarga){
			response.restart();
			try {
				response.data=downloadservice.updatedowload(id, descarga);
				 httpStatus = HttpStatus.OK;
			} catch (DataAccessException exception) {
	            getErrorMessageForResponse(exception);
	        } catch (Exception exception) {
	            getErrorMessageInternal(exception);
	        }
	        return new ResponseEntity<Response>(response, httpStatus);
		}
	  
	  
	  @DeleteMapping("/api/descarga/{id}")
	  public ResponseEntity<Response> elimarDescarga(@PathVariable("id") Integer id){
		  response.restart();
		  try {
			response.data=downloadservice.deletedowload(id);
			 if (response.data == null) {
	                response.message = "La descarga no existe";
	                httpStatus = HttpStatus.NOT_FOUND;
	            } else {
	                response.message = "La descarga fue removido exitosamente";
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
