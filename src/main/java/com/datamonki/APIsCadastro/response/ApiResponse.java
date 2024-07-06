package com.datamonki.APIsCadastro.response;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	private String menssage;
	private Object data;

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
