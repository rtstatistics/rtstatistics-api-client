/**
 * 
 */
package com.rtstatistics.client;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;

import org.junit.Test;

/**
 * @author James Hu (Zhengmao Hu)
 *
 */
public class AbstractApiClientTest {

	@Test
	public void shouldLoadCertificate() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException{
		new DataApiClient();
	}
	
	//@Test(expected=ApiClientErrorException.class) JDK 6 does not support the key length used in server certificates
	public void shouldConnect(){
		DataApiClient client = new DataApiClient("aa", "bb", "cc", "dd");
		client.send(new HashMap<String, String>());
	}
}
