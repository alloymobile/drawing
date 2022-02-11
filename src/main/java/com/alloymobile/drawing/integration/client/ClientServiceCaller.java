package com.alloymobile.drawing.integration.client;

import com.alloymobile.drawing.integration.client.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ClientServiceCaller {
    private final WebClient webClient;
    private final String clientUrl;

    public ClientServiceCaller(Environment env, final WebClient.Builder webclient) {
        this.clientUrl = env.getProperty("drawing.client-service-url");
        this.webClient = webclient.build();
    }

    public Mono<Client> getClientById(final String clientId, String token){
        return webClient.get().uri(this.clientUrl+"/clients/"+clientId)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(Client.class);
    }
}
