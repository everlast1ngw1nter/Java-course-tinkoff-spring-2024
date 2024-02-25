package edu.java;

import edu.java.clients.github.GithubWebClient;
import edu.java.clients.stackoverflow.StackOverflowWebClient;
import edu.java.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class ScrapperApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        var ghcli = (GithubWebClient) ctx.getBean("githubWebClient");
        var res1 = ghcli.fetchRepo("everlast1ngw1nter", "Java-course-tinkoff-autumn-2023")
                .block();
        System.out.println(res1);
        var socli = (StackOverflowWebClient) ctx.getBean("stackOverflowWebClient");
        var res2 = socli.fetchQuestion("78008909").block();
        System.out.println(res2);
    }
}
