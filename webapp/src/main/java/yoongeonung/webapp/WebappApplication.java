package yoongeonung.webapp;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebappApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebappApplication.class, args);
  }

  // 안좋은 방법
  @Bean
  public Hibernate5Module hibernate5Module() {
    Hibernate5Module hibernate5Module = new Hibernate5Module();
    //강제 지연 로딩 설정
//    hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,true);
    return hibernate5Module;
  }
}
