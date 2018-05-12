package com.carrental.helper;


import java.util.logging.Logger;

import org.json.simple.JSONObject;

import com.carrental.model.ErrorObject;

public class ErrorMessageUtility {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(ErrorMessageUtility.class.getName());
	
	
	/**
	 * Gets the error json.
	 *
	 * @param errorCode the error code
	 * @param erroreMessage the errore message
	 * @param errorAuxiliary the error auxiliary
	 * @return the error json
	 */
	public static ErrorObject getErrorJson (int errorCode, String errorMessage, String errorAuxiliary){
		ErrorObject error = new ErrorObject();
		error.setCode(errorCode);
		error.setErrorMessage(errorMessage);
		error.setErrorAdditional(errorAuxiliary);
		return error;
	}
	
	
	
	/**
	 * Gets the single custom json.
	 *
	 * @param code the code
	 * @param message the message
	 * @param customerMessageValue the customer message value
	 * @param auxiliary the auxiliary
	 * @return the single custom json
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getSingleCustomJson(int code, String message, String customerMessageValue, String auxiliary){
		JSONObject json = new JSONObject();
		json.put("error-code", code);
		if(message.contains("{0}") && customerMessageValue!=null){
			message = message.replace("{0}", customerMessageValue);
		}else {
			message = message.replace("{0}", "null");
		}
		json.put("error-message", message);
		json.put("error-auxiliary-message", auxiliary);
		return json;
	}
	
	
	/**
	 * Gets the success json.
	 *
	 * @param successCode the success code
	 * @param successMessage the success message
	 * @param successAuxiliary the success auxiliary
	 * @return the success json
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getSuccessJson(int successCode, String successMessage, String successAuxiliary){
		JSONObject json = new JSONObject();
		json.put("success-code", successCode);
		json.put("success-message", successMessage);
		json.put("success-auxiliary", successAuxiliary);
		return json;
	}
	
}

