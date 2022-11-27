package org.tour.services;

import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TourInfoService {
	
	private String serviceKey = "JwfJi6amNkVV1bp220AhU8hciJnWx7zMdSDx0S7PIlKR4Yfj46CWfALJNuUC4xXNXNF83NSCpWXoKxK52m%2BJYQ%3D%3D";
	
	public List<TourApiItem> process(double lng, double lat, int radius) {
		List<TourApiItem> items = null;
		
		InputStream in = null;
		HttpURLConnection conn = null;
		try {
			String apiURL = getURL(lng, lat, radius);
			URL url = new URL(apiURL);
			System.out.println(apiURL);
			ignoreSsl();
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
		
			in = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				in = conn.getErrorStream();
			}
		}
		
		if (in != null) {
			StringBuffer sb = new StringBuffer(1000);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
				String line = null;
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String result = sb.toString();
			System.out.println(result);
			
			ObjectMapper om = new ObjectMapper();
			om.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());
			om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				TourApiResponse apiResult = om.readValue(result, TourApiResponse.class);
				items = apiResult.getResponse().getBody().getItems().getItem();
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
		
		return items;
	}
	
	private String getURL(double lng, double lat, int radius) {
		StringBuffer sb = new StringBuffer("https://apis.data.go.kr/B551011/KorService/locationBasedList?");
		sb.append("serviceKey=");
		sb.append(serviceKey);
		sb.append("&MobileOS=ETC&MobileApp=");
		try {
		sb.append(URLEncoder.encode("관광", "UTF-8"));
		} catch (Exception e) {}
		sb.append("&mapX=");
		sb.append("" + lng);
		sb.append("&mapY=");
		sb.append("" + lat);
		sb.append("&radius=");
		sb.append("" + radius);
		sb.append("&_type=json");
		
		return sb.toString();
	}
	
	public static void ignoreSsl() throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

	
	private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
 
    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
 
        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }
 
        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }
 
        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
 
        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }
}
