package ru.gb.webServise.contollers;


import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ru.gb.webServise.models.domain.CardData;


import java.util.List;


@Controller
@NoArgsConstructor
public class AdminController {

    //главная страница
    @GetMapping("/admin")
    public String admin() {
        return "indexAdmin";
    }
    //проброс на главную страницу после авторизации
    @GetMapping("/")
    public String start() {
        return "redirect:/admin";
    }
    //страница работы с картами (текущие карты + импорт файла с реестром выпущенных карт)
    @GetMapping("/cardWorking")
    public String cardWorking(Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CardData>> response = restTemplate.exchange(
                "http://localhost:8080/api/v1/card/getAll",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardData>>() {}
        );
        List<CardData> cards = response.getBody();
       // System.out.println(cards);
        model.addAttribute("cards", cards);
        return "cardWorking";
    }


}
