package com.axis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.app.model.Stock;

import io.samco.client.ApiException;
import io.samco.client.api.UserLoginApi;
import io.samco.client.model.LoginRequest;
import io.samco.client.model.LoginResponse;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	static public String stockApiCall() {
		String userId = "RH15189";
		String password = "@25Harpreet";
		String yob = "1995";
	
		UserLoginApi userLoginApi = new UserLoginApi();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserId(userId);
		loginRequest.setPassword(password);
		loginRequest.setYob(yob);
		
		String sessionToken = null;
		
		try {
			LoginResponse loginResponse = userLoginApi.login(loginRequest);
			sessionToken = (String) loginResponse.getSessionToken();
			System.out.println(loginResponse);
		} catch (ApiException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return sessionToken;
		
	}
}
