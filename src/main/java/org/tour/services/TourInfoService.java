package org.tour.services;

import java.net.URL;

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
	
	private String serviceKey = "y85uY1dLyff9C7%2FlXT5agRdjB7XOpjJzUdu2DAfu3IadGmWeis3IMMDkw6NFpMGX%2Fsr3FPM15j7fM1OSgjhvPw%3D%3D";
	
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
			conn.setRequestProperty("Accept", "application/json");
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
			result = "{\"response\": {\"header\":{\"resultCode\":\"0000\",\"resultMsg\":\"OK\"},\"body\": {\"items\": {\"item\":[{\"addr1\":\"인천광역시 연수구 센트럴로 350\",\"addr2\":\"(송도동)\",\"areacode\":\"2\",\"booktour\":\"0\",\"cat1\":\"A02\",\"cat2\":\"A0202\",\"cat3\":\"A02020600\",\"contentid\":\"2767886\",\"contenttypeid\":\"12\",\"createdtime\":\"20211104000349\",\"dist\":\"10026.467516412024\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/95/2781395_image2_1.png\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/95/2781395_image2_1.png\",\"mapx\":\"126.6324764450\",\"mapy\":\"37.4079442991\",\"mlevel\":\"6\",\"modifiedtime\":\"20220506102435\",\"readcount\":0,\"sigungucode\":\"8\",\"tel\":\"\",\"title\":\"송도 도그파크\"},{\"addr1\":\"인천광역시 연수구 센트럴로 350\",\"addr2\":\"(송도동)\",\"areacode\":\"2\",\"booktour\":\"\",\"cat1\":\"A02\",\"cat2\":\"A0207\",\"cat3\":\"A02070100\",\"contentid\":\"561027\",\"contenttypeid\":\"15\",\"createdtime\":\"20080519190828\",\"dist\":\"10034.330640752447\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/71/2818671_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/71/2818671_image2_1.jpg\",\"mapx\":\"126.6324847807\",\"mapy\":\"37.4078332011\",\"mlevel\":\"6\",\"modifiedtime\":\"20220810151917\",\"readcount\":73021,\"sigungucode\":\"8\",\"tel\":\"1899-7188\",\"title\":\"인천펜타포트 락 페스티벌\"},{\"addr1\":\"인천광역시 연수구 컨벤시아대로 80 인천송도힐스테이트\",\"addr2\":\"\",\"areacode\":\"2\",\"booktour\":\"\",\"cat1\":\"A05\",\"cat2\":\"A0502\",\"cat3\":\"A05020100\",\"contentid\":\"2906508\",\"contenttypeid\":\"39\",\"createdtime\":\"20221111151647\",\"dist\":\"10053.829551066357\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/93/2906493_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/93/2906493_image3_1.jpg\",\"mapx\":\"126.6495307864\",\"mapy\":\"37.3956488051\",\"mlevel\":\"6\",\"modifiedtime\":\"20221111151710\",\"readcount\":0,\"sigungucode\":\"8\",\"tel\":\"\",\"title\":\"육꼬집 송도본점\"},{\"addr1\":\"인천광역시 연수구 신송로125번길 13\",\"addr2\":\"(송도동)\",\"areacode\":\"2\",\"booktour\":\"0\",\"cat1\":\"A02\",\"cat2\":\"A0202\",\"cat3\":\"A02020600\",\"contentid\":\"2782697\",\"contenttypeid\":\"12\",\"createdtime\":\"20211129200844\",\"dist\":\"10063.723937302106\",\"firstimage\":\"\",\"firstimage2\":\"\",\"mapx\":\"126.6511723319\",\"mapy\":\"37.3946239529\",\"mlevel\":\"6\",\"modifiedtime\":\"20211201023518\",\"readcount\":1,\"sigungucode\":\"8\",\"tel\":\"\",\"title\":\"마리앤쥬\"},{\"addr1\":\"인천광역시 연수구 컨벤시아대로 81 드림시티\",\"addr2\":\"2층\",\"areacode\":\"2\",\"booktour\":\"\",\"cat1\":\"A05\",\"cat2\":\"A0502\",\"cat3\":\"A05020100\",\"contentid\":\"2852201\",\"contenttypeid\":\"39\",\"createdtime\":\"20220908152612\",\"dist\":\"10079.950100480713\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/98/2852198_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/98/2852198_image3_1.jpg\",\"mapx\":\"126.6501779678\",\"mapy\":\"37.3949961144\",\"mlevel\":\"6\",\"modifiedtime\":\"20220908152629\",\"readcount\":0,\"sigungucode\":\"8\",\"tel\":\"\",\"title\":\"송도조개창고\"},{\"addr1\":\"인천광역시 중구 연안부두로 24-1\",\"addr2\":\"(항동7가)\",\"areacode\":\"2\",\"booktour\":\"0\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030600\",\"contentid\":\"699274\",\"contenttypeid\":\"12\",\"createdtime\":\"20090225210335\",\"dist\":\"10090.656430925592\",\"firstimage\":\"\",\"firstimage2\":\"\",\"mapx\":\"126.6027639318\",\"mapy\":\"37.4561353988\",\"mlevel\":\"6\",\"modifiedtime\":\"20220920160156\",\"readcount\":35818,\"sigungucode\":\"10\",\"tel\":\"\",\"title\":\"연안부두 밴댕이회무침거리\"},{\"addr1\":\"경기도 부천시 작동\",\"addr2\":\"(작동)\",\"areacode\":\"31\",\"booktour\":\"0\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030600\",\"contentid\":\"611883\",\"contenttypeid\":\"12\",\"createdtime\":\"20080820181724\",\"dist\":\"10103.454998061468\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/80/2480780_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/80/2480780_image2_1.jpg\",\"mapx\":\"126.8135819557\",\"mapy\":\"37.5148267542\",\"mlevel\":\"5\",\"modifiedtime\":\"20220421145437\",\"readcount\":21329,\"sigungucode\":\"11\",\"tel\":\"\",\"title\":\"까치울 먹거리촌 (작동 먹거리촌)\"},{\"addr1\":\"경기도 시흥시 서해안로 699 시흥 프리미엄 아울렛\",\"addr2\":\"\",\"areacode\":\"31\",\"booktour\":\"\",\"cat1\":\"A04\",\"cat2\":\"A0401\",\"cat3\":\"A04010600\",\"contentid\":\"2524082\",\"contenttypeid\":\"38\",\"createdtime\":\"20171221195657\",\"dist\":\"10126.027136783552\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/67/2532867_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/67/2532867_image2_1.jpg\",\"mapx\":\"126.7383981191\",\"mapy\":\"37.3801938341\",\"mlevel\":\"6\",\"modifiedtime\":\"20220404133431\",\"readcount\":2081,\"sigungucode\":\"14\",\"tel\":\"\",\"title\":\"신세계사이먼 시흥 프리미엄 아울렛\"},{\"addr1\":\"경기도 시흥시 서해안로 699 1165호(정왕동, 시흥 프리미엄아울렛)\",\"addr2\":\"\",\"areacode\":\"31\",\"booktour\":\"\",\"cat1\":\"A04\",\"cat2\":\"A0401\",\"cat3\":\"A04011000\",\"contentid\":\"2893994\",\"contenttypeid\":\"38\",\"createdtime\":\"20221031153420\",\"dist\":\"10126.027136783552\",\"firstimage\":\"\",\"firstimage2\":\"\",\"mapx\":\"126.7383981191\",\"mapy\":\"37.3801938341\",\"mlevel\":\"6\",\"modifiedtime\":\"20221123103733\",\"readcount\":0,\"sigungucode\":\"14\",\"tel\":\"\",\"title\":\"랄프로렌_신세계시흥아울렛점\"},{\"addr1\":\"경기도 시흥시 서해안로 699\",\"addr2\":\"\",\"areacode\":\"31\",\"booktour\":\"\",\"cat1\":\"A04\",\"cat2\":\"A0401\",\"cat3\":\"A04011000\",\"contentid\":\"2894830\",\"contenttypeid\":\"38\",\"createdtime\":\"20221031154323\",\"dist\":\"10126.027136783552\",\"firstimage\":\"\",\"firstimage2\":\"\",\"mapx\":\"126.7383981191\",\"mapy\":\"37.3801938341\",\"mlevel\":\"6\",\"modifiedtime\":\"20221122133303\",\"readcount\":0,\"sigungucode\":\"14\",\"tel\":\"\",\"title\":\"투미 신세계아울렛 시흥점\"}]},\"numOfRows\":10,\"pageNo\":1,\"totalCount\":2892}}}";
			
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
		sb.append("&MobileOS=ETC&MobileApp=관광&mapX=");
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
