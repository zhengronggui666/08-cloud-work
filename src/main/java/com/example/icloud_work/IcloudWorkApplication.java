package com.example.icloud_work;

import com.example.icloud_work.Util.TokenUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class IcloudWorkApplication {

    public static void main(String[] args) {
        TokenUtil bucketUtil = new TokenUtil();
        TokenUtil.buckets.put("bucket",bucketUtil);
        SpringApplication.run(IcloudWorkApplication.class, args);

    }
    @Scheduled(fixedRate = 1000)
    public void timer() {
        if (TokenUtil.buckets.containsKey("bucket")){

            TokenUtil.buckets.get("bucket").incrTokens();
        }
    }

}
