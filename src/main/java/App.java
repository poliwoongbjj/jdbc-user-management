import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean1 =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld bean2 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean1.getMessage());

        // Get Cat bean twice
        Cat cat1 = (Cat) applicationContext.getBean("cat");
        Cat cat2 = (Cat) applicationContext.getBean("cat");

        // Compare HelloWorld beans by reference (should be true - singleton scope)
        System.out.println("HelloWorld beans are the same reference: " + (bean1 == bean2));

        // Compare Cat beans by reference (should be false - prototype scope)
        System.out.println("Cat beans are the same reference: " + (cat1 == cat2));

        // Additional info
        System.out.println("HelloWorld bean hashCode: " + bean1.hashCode() + " and " + bean2.hashCode());
        System.out.println("Cat bean hashCode: " + cat1.hashCode() + " and " + cat2.hashCode());
    }
}