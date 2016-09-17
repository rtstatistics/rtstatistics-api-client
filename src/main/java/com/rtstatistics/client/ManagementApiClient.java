/**
 * 
 */
package com.rtstatistics.client;

import com.rtstatistics.client.model.DatasetKeys;
import org.springframework.http.HttpHeaders;

import com.rtstatistics.client.model.Dataset;

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
	
	/***************	/datasets	****************/
	
	/**
	 * Get all datasets
	 * @return	all datasets belonging to the organization
	 * @throws ApiClientErrorException	if got 4xx error
	 * @throws ApiServerErrorException	if got 5xx error
	 */
	public Dataset[] getAllDatasets() throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;
		
		return get("/datasets", headers, Dataset[].class);
	}
	
	/**
	 * Create a new dataset
	 * @param dataset	The dataset to be created. Its id field will be ignored.
	 * @return	an object with only the id field gives you the ID of the newly created dataset
	 * @throws ApiClientErrorException	if got 4xx error
	 * @throws ApiServerErrorException	if got 5xx error
	 */
	public Dataset createDataset(Dataset dataset) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;
		
		return post("/datasets", dataset, headers, Dataset.class);
	}
	
	/**
	 * Delete a dataset. Please note that all the statistics and their data belonging to the dataset will also be deleted.
	 * @param id	ID of the dataset
	 * @throws ApiClientErrorException	if got 4xx error
	 * @throws ApiServerErrorException	if got 5xx error
	 */
	public void deleteDataset(String id) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		delete("/datasets/" + id, headers);
	}
	
	/**
	 * Get detailed information of a dataset.
	 * @param id	ID of the dataset
	 * @return	The dataset with full detail
	 * @throws ApiClientErrorException	if got 4xx error
	 * @throws ApiServerErrorException	if got 5xx error
	 */
	public Dataset getDataset(String id) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;
		
		return get("/datasets/" + id, headers, Dataset.class);
	}
	
	/**
	 * Partially update a dataset. 
	 * @param id	ID of the dataset
	 * @param dataset	Updates. Its id field will be ignored. Those fields with null values will not be changed.
	 */
	public void updateDataset(String id, Dataset dataset){
		HttpHeaders headers = defaultHeaders;

		patch("/datasets/" + id, dataset, headers);
	}

	public DatasetKeys getDatasetKeys(String id)  throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return get("/datasets/" + id + "/keys", headers, DatasetKeys.class);
	}

	/***************	/fields	****************/



	/***************	/organizations	****************/

	/***************	/periods	****************/

	/***************	/statistics	****************/

	/***************	/users	****************/

}
