package ch.bfh.util;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MapsUtil {
    @Value("${google.apikey}")
    String apiKey;

    public GeoApiContext getApiContext(){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        return context;
    }
}
