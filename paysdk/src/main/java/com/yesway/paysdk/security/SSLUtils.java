package com.yesway.paysdk.security;

import android.content.Context;


import com.yesway.paysdk.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * https双向验证
 *
 * @author zhangke
 */
public class SSLUtils {
	// 服务器端需要验证的客户端证书
	private static final String KEY_STORE_TYPE_P12 = "PKCS12";
	private static final String keyStoreFileName = "client.p12";
	private static final String keyStorePassword = "yesway95190";

	// 客户端信任的服务器端证书
	private static final String KEY_STORE_TYPE_BKS = "bks";
	private static final String trustStoreFileName = "server.bks";
	private static final String trustStorePassword = "yesway95190";

	private static Context sContext = null;

	/**
	 * 获取SSLSocketFactory
	 * 
	 * @param ctx
	 * @return
	 */
	public static SSLSocketFactory getSSLSocketFactory(Context ctx) {

		sContext = ctx;

		try {
			SSLContext context = SSLContext.getInstance("TLS");

			KeyManager[] keyManagers = createKeyManagers(keyStoreFileName, keyStorePassword);
			TrustManager[] trustManagers = createTrustManagers(trustStoreFileName, trustStorePassword);

			context.init(keyManagers, trustManagers, null);

			return context.getSocketFactory();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 服务器端需要验证的客户端证书
	 */
	private static KeyManager[] createKeyManagers(String keyStoreFileName, String keyStorePassword)
			throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException,
			UnrecoverableKeyException {

//		InputStream inputStream = sContext.getResources().getAssets().open(keyStoreFileName);
//		InputStream inputStream = sContext.getResources().openRawResource(R.raw.client);
//		KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
//		keyStore.load(inputStream, keyStorePassword.toCharArray());
//
//		KeyManager[] managers;
//		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//
//		keyManagerFactory.init(keyStore, keyStorePassword == null ? null : keyStorePassword.toCharArray());
//		managers = keyManagerFactory.getKeyManagers();
//		return managers;

		return null;
	}

	/**
	 * 客户端信任的服务器端证书
	 */
	private static TrustManager[] createTrustManagers(String trustStoreFileName, String trustStorePassword)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

//		InputStream inputStream = sContext.getResources().getAssets().open(trustStoreFileName);
//		InputStream inputStream = sContext.getResources().openRawResource(R.raw.server);
//		KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_BKS);
//		trustStore.load(inputStream, trustStorePassword.toCharArray());
//
//		TrustManagerFactory trustManagerFactory = TrustManagerFactory
//				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//		trustManagerFactory.init(trustStore);
//		return trustManagerFactory.getTrustManagers();

		return null;
	}

	/**
	 * 获取HostnameVerifier
	 * 
	 * @return
	 */
	public static HostnameVerifier getHostnameVerifier() {
		HostnameVerifier hostnameVerifier = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		return hostnameVerifier;
	}

}
