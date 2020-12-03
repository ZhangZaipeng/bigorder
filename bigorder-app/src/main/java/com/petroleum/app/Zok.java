package com.petroleum.app;

import com.github.javafaker.Faker;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangZp on 2018/9/13.
 */
@RestController
public class Zok {

  Logger logger = LoggerFactory.getLogger(Zok.class);

  @GetMapping(value = "/ok.htm")
  public String ok() {
    logger.info("ok ok ok ");
    return "ok";
  }

  public static void main(String[] args) {
    Faker faker = new Faker(new Locale("zh-CN"));
    System.out.println(faker.app().name());
    System.out.println(faker.phoneNumber().cellPhone());
    System.out.println(faker.hitchhikersGuideToTheGalaxy().character());

    System.out.println(faker.book().title());
  }
}
