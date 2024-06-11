package ru.gb.webServise.models.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.webServise.models.dto.ClientDataDto;
import ru.gb.webServise.models.entities.CardData;
import ru.gb.webServise.models.entities.ClientData;
import ru.gb.webServise.models.entities.Product;
import ru.gb.webServise.models.entities.ProductDto;


import java.util.UUID;

//сервис для отправки запросов к другим микросервисам через API Gateway
@Service
public class ServiceIntegration {

    public ClientData createNewProfile(ClientDataDto clientDataDto){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientData> response = restTemplate.exchange(
                "http://localhost:8080/api/v1/profile/createProfile",
                HttpMethod.POST, new HttpEntity<>(clientDataDto),
                new ParameterizedTypeReference<ClientData>() {}
        );
        return response.getBody();
    }

    public ClientData findClientDataByUuid(UUID uuid){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientData> response = restTemplate.exchange(
                "http://localhost:8080/api/v1/profile/getProfileByUuid/"+uuid,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ClientData>() {}
        );

        System.out.println(response.getBody());
        return response.getBody();
    }

    public CardData findFreeCard(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CardData> response = restTemplate.exchange(
                "http://localhost:8080/api/v1/card/getFreeCard",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<CardData>() {}
        );
        return response.getBody();
    }

    public void saveProductClientData(ProductDto product){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product> response = restTemplate.exchange(
                "http://localhost:8081/api/v1/profile/product/addProduct",
                HttpMethod.POST, new HttpEntity<>(product),
                new ParameterizedTypeReference<Product>() {}
        );
        System.out.println(response.getBody() + "c'qd");
    }

    public void updateCardService(CardData cardData){
        cardData.setIsUsing(true);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CardData> response = restTemplate.exchange(
                "http://localhost:8080/api/v1/card/setUsingCard",
                HttpMethod.PUT, new HttpEntity<>(cardData),
                new ParameterizedTypeReference<CardData>() {}
        );
    }
}
