/**
 * 
 */
package com.rtstatistics.client;

import org.springframework.http.HttpHeaders;

/**
 * Client for accessing rtstatistics.com Management API.
 * @see {@link AbstractApiClient} for default behaviors. 
 *
 * @author James Hu (Zhengmao Hu)
 *
 */
public class ManagementApiClient extends AbstractApiClient {
	HttpHeaders defaultHeaders;
	HttpHeaders defaultNoContentHeaders;
	
	public ManagementApiClient(){
		super();
		this.baseUrl = "https://manage.rtstatistics.com";
		this.initializeRestTemplate();
	}

	public ManagementApiClient(String organizationKey){
		this();
		setOrganizationKey(organizationKey);
	}

	public void setOrganizationKey(String key){
		this.defaultHeaders = buildHeaders(ACCEPT_AND_OFFER_JSON, key);
		this.defaultNoContentHeaders = buildHeaders(ACCEPT_JSON, key);
	}
	

}
