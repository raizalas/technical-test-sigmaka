package com.sigmaka.caseestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CaseestudyApplication {

  public static void main(String[] args) {
    SpringApplication.run(CaseestudyApplication.class, args);
  }

}
