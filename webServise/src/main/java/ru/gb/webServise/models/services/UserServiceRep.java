package ru.gb.webServise.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.webServise.models.domain.MyUserDetails;
import ru.gb.webServise.models.entities.Authority;
import ru.gb.webServise.models.entities.User;
import ru.gb.webServise.models.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
//сервис для работы с репозиторием
@Service
public class UserServiceRep implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Override
    public MyUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = userRepository.findUserByLogin(login).orElseThrow(s);

        return new MyUserDetails(u);
    }
    public void upadateUser(User user){
        User userFromDB = userRepository.findUserByLogin(user.getLogin()).orElse(null);
        assert userFromDB != null;
        userFromDB.setUuidClient(user.getUuidClient());
        userRepository.save(userFromDB);
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findUserByLogin(user.getLogin()).orElse(null);

        if (userFromDB != null) {
            return false;
        }

        List<Authority> authorities = new ArrayList<>();

        Authority authority1 = new Authority();
        authority1.setUser(user);
        authority1.setName("READ");
        authorities.add(authority1);

        Authority authority2 = new Authority();
        authority2.setUser(user);
        authority2.setName("WRITE");
        authorities.add(authority2);

        user.setAuthorities(authorities);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void deleteUserByLogin(User user){
        User userFromDB = userRepository.findUserByLogin(user.getLogin()).orElse(null);
        if(userFromDB!=null){
        userRepository.delete(userFromDB);
        }
    }

    public User findUserByLogin(String login){
        return userRepository.findUserByLogin(login).orElse(null);
    }
}
