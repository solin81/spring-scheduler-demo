package com.fujitsu.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Task to keep the session implemented using RestTemplate
 *
 * @author Maciej Solinski <a href="mailto:maciej.solinski@ts.fujitsu.com">maciej.solinski@ts.fujitsu.com</a>
 */
@Component
public class SpringHttpRequestTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(SpringHttpRequestTask.class);

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://kashyyyk.abg.fsc.net/jira";
//        String response = restTemplate.getForObject(url, String.class);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        //new RestTemplate

        //responseEntity.getHeaders().get

        logger.info(responseEntity.getHeaders().toString());

    }

}
