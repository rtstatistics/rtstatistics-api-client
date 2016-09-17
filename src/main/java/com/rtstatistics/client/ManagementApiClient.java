/**
 * 
 */
package com.rtstatistics.client;

import com.rtstatistics.client.model.*;
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
	
	/***************	/datasets	****************/
	
	/**
	 * Get all datasets
	 * @return	all datasets belonging to the organization
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Dataset[] getAllDatasets() throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;
		
		return get("/datasets", headers, Dataset[].class);
	}
	
	/**
	 * Create a new dataset
	 * @param dataset	The dataset to be created. Its id field will be ignored.
	 * @return	an object with only the id field which gives you the ID of the newly created dataset
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Dataset createDataset(Dataset dataset) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;
		
		return post("/datasets", dataset, headers, Dataset.class);
	}
	
	/**
	 * Delete a dataset. Please note that all the statistics and their data belonging to the dataset will also be deleted.
	 * @param id	ID of the dataset
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void deleteDataset(String id) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		delete("/datasets/" + id, headers);
	}
	
	/**
	 * Get detailed information of a dataset.
	 * @param id	ID of the dataset
	 * @return	The dataset with full detail
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Dataset getDataset(String id) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;
		
		return get("/datasets/" + id, headers, Dataset.class);
	}
	
	/**
	 * Partially update a dataset. 
	 * @param id	ID of the dataset
	 * @param dataset	Updates. Its id field will be ignored. Those fields with null values will not be changed.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void updateDataset(String id, Dataset dataset) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		patch("/datasets/" + id, dataset, headers);
	}

	/**
	 * Get the send keys and query keys of a dataset
	 * @param id    ID of the dataset
	 * @return  keys belonging to the dataset
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public DatasetKeys getDatasetKeys(String id) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return get("/datasets/" + id + "/keys", headers, DatasetKeys.class);
	}

	/**
	 * Regenerate a key for the dataset.
	 * @param id        ID of the dataset
	 * @param oldKey    the old key
	 * @return          the newly generated key which replaces the old key
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public SingleKey regenerateDatasetKey(String id, String oldKey) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return put("/datasets/" + id + "/keys/" + oldKey, null, headers, SingleKey.class);
	}

	/**
	 * Get recent data items appended to the dataset.
	 * @param datasetId     ID of the dataset
	 * @return      data items appended to the dataset recently
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public ReceivedDataItem[] getDatasetRecentDataItems(String datasetId) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return get("/datasets/" + datasetId + "/recent", headers, ReceivedDataItem[].class);
	}

	/**
	 * Get all statistics defined on the dataset
	 * @param datasetId     ID of the dataset
	 * @return      All statistics defined on the dataset. Only summaries will be returned.
	 *              To retrieve details, please use {@link #getStatistics(String)} method.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Statistics[] getDatasetStatistics(String datasetId) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return get("/datasets/" + datasetId + "/statistics", headers, Statistics[].class);
	}

	/***************	/fields	****************/

	/**
	 * Get all fields defined on the dataset
	 * @param datasetId     ID of the dataset
	 * @return      All fields defined on the dataset.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Field[] getFields(String datasetId) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return get("/datasets/" + datasetId + "/fields", headers, Field[].class);
	}

	/**
	 * Create a new field on the dataset
	 * @param datasetId ID of the dataset
	 * @param field definition of the field
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void createField(String datasetId, Field field) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		post("/datasets/" + datasetId + "/fields", field, headers, Void.class);
	}

	/**
	 * Delete a field from the dataset
	 * @param datasetId     ID of the dataset
	 * @param fieldName     name of the field
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void deleteField(String datasetId, String fieldName) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		delete("/datasets/" + datasetId + "/fields/" + fieldName, headers);
	}

	/**
	 * Get full detail of the field
	 * @param datasetId     ID of the dataset
	 * @param fieldName     name of the field
	 * @return  Full detail of the field
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Field getField(String datasetId, String fieldName) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		return get("/datasets/" + datasetId + "/fields/" + fieldName, headers, Field.class);
	}

	/**
	 * Partially update a field
	 * @param datasetId     ID of the dataset
	 * @param fieldName     name of the field
	 * @param field         Updates. Its name field will be ignored. Those fields with null values will not be changed.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void updateField(String datasetId, String fieldName, Field field) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		patch("/datasets/" + datasetId + "/fields/" + fieldName, field, headers);
	}

	/***************	/organizations	****************/

	/***************	/periods	****************/

	/**
	 * Get the summaries of all statistics periods hierarchies.
	 * @return  Summaries of all statistics periods hierarchies.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public StatisticsPeriodsHierarchy[] getAllPeriods() throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		return get("/periods", headers, StatisticsPeriodsHierarchy[].class);
	}

	/**
	 * Create a new statistics periods hierarchy
	 * @param periods   the new statistics periods hierarchy
	 * @return      an object with only the id field which gives you the ID of the newly created statistics periods hierarchy
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public StatisticsPeriodsHierarchy createPeriods(StatisticsPeriodsHierarchy periods) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		return post("/periods", periods, headers, StatisticsPeriodsHierarchy.class);
	}

	/**
	 * Delete a statistics periods hierarchy
	 * @param id    ID of the statistics periods hierarchy
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void deletePeriods(String id) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		delete("/periods/" + id, headers);
	}

	/**
	 * Get the full detail of a statistics periods hierarchy
	 * @param id    ID of the statistics periods hierarchy
	 * @return  Full detail of the statistics periods hierarchy
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public StatisticsPeriodsHierarchy getPeriods(String id) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		return get("/periods/" + id, headers, StatisticsPeriodsHierarchy.class);
	}

	/**
	 * Partially update a statistics periods hierarchy
	 * @param id        ID of the statistics periods hierarchy
	 * @param periods   Updates. Its name field will be ignored. Those fields with null values will not be changed.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void updatePeriods(String id, StatisticsPeriodsHierarchy periods) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		patch("/periods/" + id, periods, headers);
	}


	/***************	/statistics	****************/

	/**
	 * Get the summaries of all statistics.
	 * @return  Summaries of all statistics.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Statistics[] getAllStatistics() throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		return get("/statistics", headers, Statistics[].class);
	}

	/**
	 * Create a new statistics
	 * @param statistics   the new statistics
	 * @return      an object with only the id field which gives you the ID of the newly created statistics
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Statistics createStatistics(Statistics statistics) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		return post("/statistics", statistics, headers, Statistics.class);
	}

	/**
	 * Delete a statistics
	 * @param id    ID of the statistics
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void deleteStatistics(String id) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		delete("/statistics/" + id, headers);
	}

	/**
	 * Get detail of a statistics definition
	 * @param id    ID of the statistics
	 * @return      Full detail of the statistics
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public Statistics getStatistics(String id) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return get("/statistics/" + id, headers, Statistics.class);
	}

	/**
	 * Partially update a statistics
	 * @param id        ID of the statistics
	 * @param statistics   Updates. Its id field will be ignored. Those fields with null values will not be changed.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public void updateStatistics(String id, Statistics statistics) throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		patch("/statistics/" + id, statistics, headers);
	}


	/***************	/users	****************/

	/**
	 * Get the summaries of all users.
	 * @return  Summaries of all users.
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public User[] getAllUsers() throws ApiClientErrorException, ApiServerErrorException {
		HttpHeaders headers = defaultHeaders;

		return get("/users", headers, User[].class);
	}

	/**
	 * Get detail of a user
	 * @param id    ID of the user
	 * @return      Full detail of the user
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public User getUser(String id) throws ApiClientErrorException, ApiServerErrorException{
		HttpHeaders headers = defaultHeaders;

		return get("/users/" + id, headers, User.class);
	}


}
