package br.com.ace.userserviceapi.creator;

import org.springframework.stereotype.Component;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Component
public class CreatorUtils {

    private static final PodamFactory factory = new PodamFactoryImpl();

    public static <T> T generateMock(Class<T> clazz){
        return factory.manufacturePojo(clazz);
    }
}
