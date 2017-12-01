package ch.bfh.util;

import com.google.maps.GeoApiContext;
import org.springframework.stereotype.Component;

@Component
public class MapsUtil {

    public GeoApiContext getApiContext(){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDz-v21pI09JnAU8qeWfwZ8X4Gu5bcQQ6k")
                .build();
        return context;
    }
}
