/**
 * 
 */
package com.rtstatistics.client;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpRequestRetryHandler;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import net.sf.jabb.cjtsd.PlainCJTSD;
import net.sf.jabb.spring.rest.AbstractRestClient;
import net.sf.jabb.spring.rest.CustomResponseErrorHandler;
import net.sf.jabb.util.parallel.BackoffStrategies;
import net.sf.jabb.util.parallel.WaitStrategies;

/**
 * Parent class of rtstatistics.com API client.
 * The client may retry failed GET requests for no more than 10 times. The intervals between retries
 * start from 1 second and grow as Fibonacci series until 60 seconds is reached.
 * 
 * @author James Hu (Zhengmao Hu)
 *
 */
class AbstractApiClient extends AbstractRestClient {
	protected int retryMaxTimes = 10;
	protected long retryIntervalStartMillis = 1000L;
	protected int retryIntervalMaxSeconds = 60;
	
	static protected final ParameterizedTypeReference<ApiResponseBody<String[]>> RESPONSE_BODY_IDS = new ParameterizedTypeReference<ApiResponseBody<String[]>>(){};
	static protected final ParameterizedTypeReference<ApiResponseBody<PlainCJTSD>> RESPONSE_BODY_CJTSD = new ParameterizedTypeReference<ApiResponseBody<PlainCJTSD>>(){};

	@Override
	protected HttpRequestRetryHandler buildRequestRetryHandler(){
		return this.buildRequestRetryHandler(retryMaxTimes, true, true, true, 
				BackoffStrategies.fibonacciBackoff(retryIntervalStartMillis, retryIntervalMaxSeconds, TimeUnit.SECONDS), 
				WaitStrategies.threadSleepStrategy(), null);
	}
	
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters){
		for (HttpMessageConverter<?> converter: converters){
			if (converter instanceof MappingJackson2HttpMessageConverter){
				((MappingJackson2HttpMessageConverter) converter).setObjectMapper(Jackson2.objectMapper);
			}
		}
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate){
		restTemplate.setErrorHandler(new CustomResponseErrorHandler(){
			@Override
			protected void handleClientError(HttpStatus statusCode, ClientHttpResponse response) throws IOException{
				throw new ApiClientErrorException(statusCode, response.getStatusText(),
						response.getHeaders(), getResponseBody(response), getCharset(response));
			}
			
			@Override
			protected void handleServerErro(HttpStatus statusCode, ClientHttpResponse response) throws IOException{
					throw new ApiServerErrorException(statusCode, response.getStatusText(),
							response.getHeaders(), getResponseBody(response), getCharset(response));
			}
		});
	}
	
	protected HttpHeaders buildHeaders(HttpHeaders base, String apiKey){
		HttpHeaders headers = copy(base);
		addBasicAuthHeader(headers, apiKey);
		return HttpHeaders.readOnlyHttpHeaders(headers);
	}
	
	public void setBaseUrl(String baseUrl){
		this.baseUrl = baseUrl;
	}
}
