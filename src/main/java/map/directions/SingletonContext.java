package map.directions;

import java.io.IOException;

import com.google.maps.GeoApiContext;

import config.ApiConfig;

public class SingletonContext {
	
	private static SingletonContext singletonContext;
	public GeoApiContext context = null;

	private SingletonContext(String apiKey) {
		this.context = new GeoApiContext.Builder()
			    .apiKey(apiKey)
			    .build();
	}
	
	public static SingletonContext getContext(String env, String encryptionPassword) throws IOException {
		if(singletonContext == null) {
			String apiKey = ApiConfig.decryptApiKey(env, encryptionPassword);
			singletonContext = new SingletonContext(apiKey);
		}
		return singletonContext;
	}
}
