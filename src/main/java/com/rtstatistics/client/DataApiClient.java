/**
 * 
 */
package com.rtstatistics.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpHeaders;

import net.sf.jabb.cjtsd.PlainCJTSD;

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
	String defaultStatisticsId;
	
	public DataApiClient(){
		super();
		this.baseUrl = "https://api.rtstatistics.com";
		this.initializeRestTemplate();
	}

	public DataApiClient(String defaultDatasetId, String defaultSendKey, String defaultQueryKey, String defaultStatisticsId){
		this();
		setDefaultDatasetId(defaultDatasetId);
		setDefaultSendKey(defaultSendKey);
		setDefaultQueryKey(defaultQueryKey);
		setDefaultStatisticsId(defaultStatisticsId);
	}

	public void setDefaultDatasetId(String defaultDatasetId){
		this.defaultDatasetId = defaultDatasetId;
	}
	
	public void setDefaultSendKey(String defaultSendKey) {
		this.defaultSendHeaders = buildHeaders(ACCEPT_AND_OFFER_JSON, defaultSendKey);
	}

	public void setDefaultQueryKey(String defaultQueryKey) {
		this.defaultQueryHeaders = buildHeaders(ACCEPT_JSON, defaultQueryKey);
	}
	
	public void setDefaultStatisticsId(String defaultStatisticsId){
		this.defaultStatisticsId = defaultStatisticsId;
	}
	
	/**
	 * Send data item(s) to rtstatistics.com.
	 * Default datasetId and API key set by {@link #setDefaultDatasetId(String)} and {@link #setDefaultSendKey(String)} will be used.
	 * The server will respond after the data is sent to the internal processing queue but before it is successfully persisted in the queue.
	 * This method provides better throughput but if you would like to guarantee that the data is successfully persisted therefore it will eventually get processed,
	 * please use {@link #send(Object, boolean)} method with <code>sync</code> parameter set to <code>true</code>.
	 * @param data			data item or data items (if it is an array or instance of Collection)
	 * @return				IDs of the data items successfully appended to the dataset 
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public String[] send(Object data) throws ApiClientErrorException, ApiServerErrorException{
		return send(null, data, null, false);
	}

	/**
	 * Send data item(s) to rtstatistics.com.
	 * Default datasetId and API key set by {@link #setDefaultDatasetId(String)} and {@link #setDefaultSendKey(String)} will be used.
	 * @param data			data item or data items (if it is an array or instance of Collection)
	 * @param sync			Whether the server should respond only after the data is successfully queued for processing
	 * 	                	in which case the data is guaranteed to be processed eventually.
	 * 	                	Setting this parameter to true is not recommended for high throughput scenarios but allows
	 * 	                	the client to catch an ApiServerErrorException with status code 429 which would happen when
	 * 	                	the client is sending data too fast.
	 * 	                	If this parameter is set to false, the server will respond after
	 * 	                	the data is sent to the internal processing queue but before it is successfully persisted in the queue.
	 * @return				IDs of the data items successfully appended to the dataset
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public String[] send(Object data, boolean sync) throws ApiClientErrorException, ApiServerErrorException{
		return send(null, data, null, sync);
	}

	/**
	 * Send data item(s) to rtstatistics.com.
	 * The server will respond after the data is sent to the internal processing queue but before it is successfully persisted in the queue.
	 * This method provides better throughput but if you would like to guarantee that the data is successfully persisted therefore it will eventually get processed,
	 * please use {@link #send(String, Object, String, boolean)}  method with <code>sync</code> parameter set to <code>true</code>.
	 * @param datasetId		ID of the dataset, or null if the datasetId set by  {@link #setDefaultDatasetId(String)} should be used.
	 * @param data			data item or data items (if it is an array or instance of Collection)
	 * @param sendKey		API key for sending to the specified dataset, if it is null,
	 * 						API key set through {@link #setDefaultSendKey(String)} will be used.
	 * @return				IDs of the data items successfully appended to the dataset
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public String[] send(String datasetId, Object data, String sendKey) throws ApiClientErrorException, ApiServerErrorException {
		return send(datasetId, data, sendKey, false);
	}

	/**
	 * Send data item(s) to rtstatistics.com.
	 * @param datasetId		ID of the dataset, or null if the datasetId set by  {@link #setDefaultDatasetId(String)} should be used.
	 * @param data			data item or data items (if it is an array or instance of Collection)
	 * @param sendKey		API key for sending to the specified dataset, if it is null,
	 * 						API key set through {@link #setDefaultSendKey(String)} will be used.
	 * @param sync			Whether the server should respond only after the data is successfully queued for processing
	 * 	                	in which case the data is guaranteed to be processed eventually.
	 * 	                	Setting this parameter to true is not recommended for high throughput scenarios but allows
	 * 	                	the client to catch an ApiServerErrorException with status code 429 which would happen when
	 * 	                	the client is sending data too fast.
	 * 	                	If this parameter is set to false, the server will respond after
	 * 	                	the data is sent to the internal processing queue but before it is successfully persisted in the queue.
	 * @return				IDs of the data items successfully appended to the dataset
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public String[] send(String datasetId, Object data, String sendKey, boolean sync) throws ApiClientErrorException, ApiServerErrorException{
		if (datasetId == null){
			datasetId = defaultDatasetId;
		}
		
		HttpHeaders headers = sendKey == null ? defaultSendHeaders : buildHeaders(ACCEPT_AND_OFFER_JSON, sendKey);

		URIBuilder builder = uriBuilder("/datasets/" + datasetId + "/items");
		if (sync) {
			builder.addParameter("sync", "true");
		}
		return post(buildUri(builder), data, headers, String[].class);
	}

	/**
	 * Query statistics detail using the default statistics ID and dataset query key
	 * @param parameters	all parameters, can be of type {@link QueryParameters}
	 * @return	statistics detail
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public PlainCJTSD query(Map<String, String> parameters) throws ApiClientErrorException, ApiServerErrorException{
		return query(null, parameters, null);
	}

	/**
	 * Query statistics detail using the default dataset query key
	 * @param statisticsId	Statistics ID
	 * @param parameters	all parameters, can be of type {@link QueryParameters}
	 * @return	statistics detail
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public PlainCJTSD query(String statisticsId, Map<String, String> parameters) throws ApiClientErrorException, ApiServerErrorException{
		return query(statisticsId, parameters, null);
	}

	/**
	 * Query statistics detail
	 * @param statisticsId	Statistics ID
	 * @param parameters	all parameters, can be of type {@link QueryParameters}
	 * @param queryKey		Query key of the dataset
	 * @return	statistics detail
	 * @throws ApiClientErrorException	if the operation was not successful because of authentication, permission control, input validation, not found, rate limiting, or other issues.
	 * @throws ApiServerErrorException	if the operation failed due to error happened on the server side.
	 */
	public PlainCJTSD query(String statisticsId, Map<String, String> parameters, String queryKey) throws ApiClientErrorException, ApiServerErrorException{
		if (statisticsId == null){
			statisticsId = defaultStatisticsId;
		}
		
		HttpHeaders headers = queryKey == null ? defaultQueryHeaders : buildHeaders(ACCEPT_JSON, queryKey);
		
		URIBuilder builder = uriBuilder("/statistics/" + statisticsId + "/detail");
		if (parameters != null){
			for (Map.Entry<String, String> entry: parameters.entrySet()){
				if (entry.getValue() != null){
					builder.addParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		
		return get(buildUri(builder), headers, PlainCJTSD.class);
	}
	
	public static class QueryParameters extends HashMap<String, String>{
		private static final long serialVersionUID = -159377479871209375L;
		
		public static final String COLUMNS = "_columns";
		public static final String FROM = "_from";
		public static final String TO = "_to";
		public static final String PERIOD = "_period";
		public static final String ZONE = "_zone";
		public static final String ABSENT = "_absent";
		
		public QueryParameters setColumns(String... columns){
			this.put(COLUMNS, StringUtils.join(columns, ','));
			return this;
		}
		
		public QueryParameters setFrom(String from){
			this.put(FROM, from);
			return this;
		}

		public QueryParameters setTo(String to){
			this.put(TO, to);
			return this;
		}

		public QueryParameters setPeriod(String period){
			this.put(PERIOD, period);
			return this;
		}

		public QueryParameters setPeriod(int amount, String unit){
			this.put(PERIOD, Integer.toString(amount) + " " + unit);
			return this;
		}
		
		public QueryParameters setPeriodOfMinutes(int amount){
			return this.setPeriod(amount, "MINUTE");
		}
		
		public QueryParameters setPeriodOfHours(int amount){
			return this.setPeriod(amount, "HOUR");
		}
		
		public QueryParameters setPeriodOfDays(int amount){
			return this.setPeriod(amount, "DAY");
		}
		
		public QueryParameters setPeriodOfMonths(int amount){
			return this.setPeriod(amount, "MONTH");
		}
		
		public QueryParameters setPeriodOfYears(int amount){
			return this.setPeriod(amount, "YEAR");
		}
		
		public QueryParameters setTimeZone(String timeZone){
			this.put(ZONE, timeZone);
			return this;
		}
		
		public QueryParameters setPeriodAndTimeZone(int amount, String unit, String timeZone){
			return this.setPeriod(amount, unit).setTimeZone(timeZone);
		}

		public QueryParameters setAbsentAsEmpty(boolean absentAsEmpty){
			if (absentAsEmpty){
				this.put(ABSENT, "empty");
			}else{
				this.remove(ABSENT);
			}
			return this;
		}

	}

}
