package com.pahlsoft.utilities.engine;


import com.pahlsoft.utilities.generator.LocomotiveGenerator;
import com.pahlsoft.utilities.interfaces.LocomotiveEngine;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Properties;

public class LocomotiveGeneratorEngine implements LocomotiveEngine {

    static Logger log = LoggerFactory.getLogger(LocomotiveGeneratorEngine.class);

    private static final String PROPERTY_ES_HOST = "elasticsearch.host";
    private static final String PROPERTY_ES_PORT = "elasticsearch.port";
    private static final String PROPERTY_ES_SCHEME = "elasticsearch.scheme";
    private static final String PROPERTY_ES_USER = "elasticsearch.user";
    private static final String PROPERTY_ES_PW = "elasticsearch.password";
    private static final String PROPERTY_KEYSTORE_LOC = "keystore.location";
    private static final String PROPERTY_KEYSTORE_PW = "keystore.password";

    private Properties properties = new Properties();

     KeyStore truststore = null;
     SSLContextBuilder sslBuilder = null;

    public LocomotiveGeneratorEngine() throws IOException {
        this.loadProperties();
    }

    private void loadProperties() throws IOException {
        Thread currentThread = Thread.currentThread();
        ClassLoader contextClassLoader = currentThread.getContextClassLoader();
        InputStream propertiesStream = contextClassLoader.getResourceAsStream("config.properties");
        if(propertiesStream != null) {
            this.properties.load(propertiesStream);
        } else {
            log.error("No Properties Found");
        }
    }

    @Override
    public void run() {
        if (log.isInfoEnabled()) {
            log.info(MessageFormat.format("Starting Account Generation Engine {0}", Thread.currentThread().getId()));
        }

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(this.properties.getProperty(PROPERTY_ES_USER),this.properties.getProperty(PROPERTY_ES_PW)));

        RestHighLevelClient client;
        IndexRequest indexRequest;

        try {
            if (this.properties.getProperty(PROPERTY_ES_SCHEME).contentEquals("https")) {
                sslBuilder = buildSSLContext();
                final SSLContext sslContext = sslBuilder.build();
                client = getSecureClient(credentialsProvider, sslContext);

            } else {
                client = getClient(credentialsProvider);
            }

            boolean engineRun = true;
            while(engineRun) {
                try {
                    indexRequest = new IndexRequest("locomotive-events", "doc").source(LocomotiveGenerator.buildLocomotive());
                    client.index(indexRequest, RequestOptions.DEFAULT);
                    log.debug("|TRAIN-DATA|");
                } catch (Exception ioe) {
                    engineRun = false;
                    log.warn(ioe.getMessage());
                }
            }
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.warn(e.getMessage());
        }
    }

    private SSLContextBuilder buildSSLContext() {
        try {
            truststore = KeyStore.getInstance("jks");
        } catch (KeyStoreException e) {
            log.warn(e.getMessage());
        }
        try (InputStream is = Files.newInputStream(Paths.get(this.properties.getProperty(PROPERTY_KEYSTORE_LOC)))) {
            truststore.load(is, this.properties.getProperty(PROPERTY_KEYSTORE_PW).toCharArray());
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        try {
            sslBuilder = SSLContexts.custom()
                    .loadTrustMaterial(truststore, null);
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            log.warn(e.getMessage());
        }
        return sslBuilder;
    }

    private RestHighLevelClient getSecureClient(CredentialsProvider credentialsProvider, SSLContext sslContext) {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(this.properties.getProperty(PROPERTY_ES_HOST), Integer.parseInt(this.properties.getProperty(PROPERTY_ES_PORT)), this.properties.getProperty(PROPERTY_ES_SCHEME)))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider).setSSLContext(sslContext);
                            }
                        }));
    }

    private RestHighLevelClient getClient(CredentialsProvider credentialsProvider) {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(this.properties.getProperty(PROPERTY_ES_HOST), Integer.parseInt(this.properties.getProperty(PROPERTY_ES_PORT)), this.properties.getProperty(PROPERTY_ES_SCHEME)))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }));
    }

}