/**
 * 
 */
package com.rtstatistics.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * Client for accessing rtstatistics.com Data API.
 * @see {@link AbstractApiClient} for default behaviors. 
 *
 * @author James Hu (Zhengmao Hu)
 *
 */
public class DataApiClient extends AbstractApiClient {
	String defaultDatasetId;
	HttpHeaders defaultSendHeaders;
	HttpHeaders defaultQueryHeaders;
	
	public DataApiClient(){
		super();
		this.baseUrl = "https://api.rtstatistics.com";
		this.initializeRestTemplate();
	}

	public DataApiClient(String defaultDatasetId, String defaultSendKey, String defaultQueryKey){
		this();
		setDefaultDatasetId(defaultDatasetId);
		setDefaultSendKey(defaultSendKey);
		setDefaultQueryKey(defaultQueryKey);
	}

	public void setDefaultDatasetId(String defaultDatasetId){
		this.defaultDatasetId = defaultDatasetId;
	}
	
	public void setDefaultSendKey(String defaultSendKey) {
		this.defaultSendHeaders = buildHeaders(ACCEPT_AND_OFFER_JSON, defaultSendKey);
	}

	public void setDefaultQueryKey(String defaultQueryKey) {
		this.defaultQueryHeaders = buildHeaders(ACCEPT_AND_OFFER_JSON, defaultQueryKey);
	}
	
	/**
	 * Send data item(s) to rtstatistics.com.
	 * Default datasetId and API key set by {@link #setDefaultDatasetId(String)} and {@link #setDefaultSendKey(String)} will be used.
	 * @param data			data item or data items (if it is an array or instance of Collection)
	 * @return				IDs of the data items successfully appended to the dataset 
	 * @throws ApiClientErrorException	if got 4xx error
	 * @throws ApiServerErrorException	if got 5xx error
	 */
	public String[] send(Object data) throws ApiClientErrorException, ApiServerErrorException{
		return send(null, data, null);
	}

	/**
	 * Send data item(s) to rtstatistics.com
	 * @param datasetId		ID of the dataset, or null if the datasetId set by  {@link #setDefaultDatasetId(String)} should be used.
	 * @param data			data item or data items (if it is an array or instance of Collection)
	 * @param sendKey		API key for sending to the specified dataset, if it is null, 
	 * 						API key set through {@link #setDefaultSendKey(String)} will be used.
	 * @return				IDs of the data items successfully appended to the dataset 
	 * @throws ApiClientErrorException	if got 4xx error
	 * @throws ApiServerErrorException	if got 5xx error
	 */
	public String[] send(String datasetId, Object data, String sendKey) throws ApiClientErrorException, ApiServerErrorException{
		if (datasetId == null){
			datasetId = defaultDatasetId;
		}
		
		HttpHeaders headers = sendKey == null ? defaultSendHeaders : buildHeaders(ACCEPT_AND_OFFER_JSON, sendKey);
		
		ResponseEntity<ApiResponseBody<String[]>> response = this.restTemplate.exchange(buildUri("/datasets/" + datasetId + "/items"), HttpMethod.POST, 
				new HttpEntity<Object>(data, headers), RESPONSE_BODY_IDS);
		String[] ids =response.getBody().getResult();
		return ids;
	}


}
