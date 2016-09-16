/**
 * 
 */
package com.rtstatistics.client;

import com.rtstatistics.client.model.ErrorDetail;

/**
 * Supplier/provider of ErrorDetail
 * @author James Hu (Zhengmao Hu)
 *
 */
public interface ErrorDetailSupplier {
	ErrorDetail getError();
}
