package com.chen.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/customer")
    public String customer() {
        InstanceInfo info = eurekaClient.getNextServerFromEureka("SEARCH", false);
        String url = info.getHomePageUrl();
        return restTemplate.getForObject(url + "/search", String.class);
      /*  String result = restTemplate.getForObject("http://SEARCH/search", String.class);
        return result;*/
    }
}
