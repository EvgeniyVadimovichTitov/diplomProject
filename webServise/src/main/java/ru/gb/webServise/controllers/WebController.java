package ru.gb.webServise.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.webServise.models.dto.ClientDataDto;
import ru.gb.webServise.models.dto.UserDto;
import ru.gb.webServise.models.entities.*;
import ru.gb.webServise.models.services.ServiceIntegration;
import ru.gb.webServise.models.services.UserServiceRep;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserServiceRep userService;

    private final ServiceIntegration serviceIntegration;
    //метрика для дашборда в Grafana
    private final Counter countAuthorityLK = Metrics.counter("count_authority_LK");


    //главная страница
    @GetMapping("/bank")
    public String main() {
        return "index";
    }

    //проброс на страницу банка
    @GetMapping("/")
    public String tart() {
        return "redirect:/bank";
    }

    //проброс на страницу авторизации
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //личный кабинет
    @GetMapping("/LK")
    public String clientLk(Model model) {
        //получаем текущего пользователя
        User user = userService.findUserByLogin(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        //получаем продукты открыте у текущего пользователя
        List<Product> products = serviceIntegration.findClientDataByUuid(user.getUuidClient()).getProducts();
        //прокидываем на верстку
        model.addAttribute("products", products);
        //инкремент в количество входов в личный кабинет
        countAuthorityLK.increment();

        return "LK";
    }

    //страница регистрации
    @GetMapping("/registrationClient")
    public String registration(Model model) {
        model.addAttribute("eserForm", new UserDto());
        return "registrationClient";
    }
    //регистрация пользователя по данным из формы на странице
    @PostMapping("/registrationClient/addUser")
    public String addUser(UserDto userForm, Model model){

        //проверка совпадения паролей
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registrationClient";
        }
        //создание нового пользователя
        User user = new User();
        user.setLogin(userForm.getLogin());
        user.setPassword(userForm.getPassword());
        //сохранение нового пользователя с проверкой на уникальность
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registrationClient";
        }
        //заполнение профиля и отправка в микросервис работы с профилем клиента
        ClientData clientData = serviceIntegration.createNewProfile(new ClientDataDto(userForm.getName(), userForm.getSurname(), userForm.getPatronymic(), userForm.getPhoneNumber()));
        //если вернулся нулл значит профиль не создался удаляем пользователя (заглушка)
        if(clientData==null){
            userService.deleteUserByLogin(user);
            return "registrationClient";
        }
        //получаем идентификатор профиля и сохраняем его в пользователя
        user.setUuidClient(clientData.getUuid());
        userService.upadateUser(user);

        return "redirect:/";
    }
    //создание нового продукта из формы в личном кабинете
    @PostMapping("/createNewProduct")
    public String createNewProduct(Model model){
        //получаем логин текущего пользователя и ищем его в бд для поиска идентификатора профиля клиента
        User user = userService.findUserByLogin(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        //по идентификатору профиля клиента ролучаем полный профиль
        ClientData clientData = serviceIntegration.findClientDataByUuid(user.getUuidClient());
        //получаем новую свободную карту от сервиса карт
        CardData cardData = serviceIntegration.findFreeCard();
        //создаем новыц продукт клиенту
        ProductDto product = new ProductDto();
        product.setBalance(new BigDecimal(1000));
        product.setName(ProductName.CARD_VOZM);
        product.setNumber(cardData.getCardNumber());
        product.setMainCard(true);
        product.setClient(clientData.getUuid());

        //отправляем новыц продукт в сервис профиля клиента
        serviceIntegration.saveProductClientData(product);
        //оповещаем сервис карт, что карта теперь занята
        serviceIntegration.updateCardService(cardData);
        //обновляем личныц кабинет
        return "redirect:/LK";
    }
}
