package com.fujitsu.task;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Maciej Solinski <a href="mailto:maciej.solinski@ts.fujitsu.com">maciej.solinski@ts.fujitsu.com</a>
 */
@Component
public class ApacheHttpClientRequestTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTask.class);
    private final String url = "http://kashyyyk.abg.fsc.net/jira";
    @Autowired
    private CloseableHttpClient httpClient;

    @Override
    public void run() {
        HttpGet httpget = new HttpGet(url);

//
//        // So we can get all the headers (not just the ones we explicitly set).
//        httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
//
//            public void process(
//                    final HttpRequest request,
//                    final HttpContext context)
//                    throws HttpException, IOException {
//
//                // Start Debug
//                System.out.println("*** Request headers ***");
//                Header[] requestHeaders = request.getAllHeaders();
//                for(Header header : requestHeaders) {
//                    System.out.println(header.toString());
//                }
//                System.out.println("***********************");
//                // End Debug
//            }
//
//        });

        for (Header header : httpget.getAllHeaders()) {
            LOGGER.info(header.getName() + "=" + header.getValue());
        }

        try {
            CloseableHttpResponse response = httpClient.execute(httpget);
//            LOGGER.info(String.valueOf(response.getStatusLine().getStatusCode()));
            LOGGER.info(response.toString());
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }

}
