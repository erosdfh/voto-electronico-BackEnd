package com.votoElectronico.beans;

import java.time.Instant;

import org.springframework.web.context.request.WebRequest;

import com.votoElectronico.data.ApiError;

public class ApiErrorBeans {
	
public ApiError createApiError(WebRequest request) {
	ApiError apiError= new ApiError();
	apiError.setInstant(Instant.now());
	apiError.setPath(request.getContextPath());
	return apiError;
}
}
