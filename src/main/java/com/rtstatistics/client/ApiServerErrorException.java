/**
 * 
 */
package com.rtstatistics.client;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import com.rtstatistics.client.model.ErrorDetail;

/**
 * Exception thrown when an HTTP 5xx is received.
 * 
 * @author James Hu (Zhengmao Hu)
 *
 */
public class ApiServerErrorException extends HttpServerErrorException implements ErrorDetailSupplier{
	private static final long serialVersionUID = -2649566403783674428L;
	private static final Logger logger = LoggerFactory.getLogger(ApiServerErrorException.class);

	protected ErrorDetail error;
	
	/**
	 * Construct a new instance of {@code ApiHttpServerErrorException} based on a
	 * {@link HttpStatus}, status text, and response body content.
	 * @param statusCode the status code
	 * @param statusText the status text
	 * @param responseHeaders the response headers, may be {@code null}
	 * @param responseBody the response body content, may be {@code null}
	 * @param responseCharset the response body charset, may be {@code null}
	 */
	public ApiServerErrorException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders,
			byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
		populateErrorInfo(statusCode, responseBody);
	}
	
	protected void populateErrorInfo(HttpStatus statusCode, byte[] body){
		if (body != null){
			try {
				error = Jackson2.objectMapper.readValue(body, ErrorDetail.class);
			} catch (Exception e) {
				logger.warn("Unabled to retrieve error detail: {}", body, e);
			}
		}
		if (error == null){
			error = new ErrorDetail(statusCode);
		}
	}

	@Override
	public ErrorDetail getError() {
		return error;
	}
}
