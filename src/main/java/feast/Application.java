package feast;

import feast.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            TraditionalFeast traditionalFeast =
                    applicationContext.getBean(TraditionalFeast.class);
            System.out.println(traditionalFeast.getTraditionalFeast());
        } finally {
            applicationContext.close();
        }
    }
}