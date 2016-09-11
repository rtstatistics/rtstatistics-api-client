/**
 * 
 */
package com.rtstatistics.client;

/**
 * Provider of ErroInfo
 * @author James Hu (Zhengmao Hu)
 *
 */
public interface ApiErrorInfoProvider {
	ApiResponseBody.ErrorInfo getError();
}
