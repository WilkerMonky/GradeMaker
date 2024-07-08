package com.datamonki.APIsCadastro.response;
import com.google.gson.Gson;

public class ApiResponse {
	private String message;
	private Object data;

	public ApiResponse(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
