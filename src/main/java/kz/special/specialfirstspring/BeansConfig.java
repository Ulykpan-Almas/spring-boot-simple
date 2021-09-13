package kz.special.specialfirstspring;

import kz.special.specialfirstspring.beans.OtherBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"kz.special.specialfirstspring"})
public class BeansConfig {

    @Bean
    public OtherBean otherBean(){
        return new OtherBean();
    }

    @Bean
    public OtherBean otherBeanWithConstructor(){
        return new OtherBean("SAMLA",20);
    }

    @Bean(name = "beanBek")
    public OtherBean otherBeanBek(){
        return new OtherBean("Bean Bek",11);
    }


}
