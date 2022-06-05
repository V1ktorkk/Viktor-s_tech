package ru.itmo.kotikilab5controllers.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab5controllers.kafkaCommunication.Communicate;
import ru.itmo.kotikilab5controllers.kafkaCommunication.Message;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private Message message;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                return authorities;
            }

            @Override
            public String getPassword() {
                return "$2a$10$Z6pSU8276INJqfs7JeRUM.0YUiJiH3J6DJZRaG6PZ1uuN/Pb8rWvm";
            }

            @Override
            public String getUsername() {
                return "test";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
    };
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       return  Message.serializeFromJson(
//               message.sendMessage(Message.serializeToJson(Message.serializeToJson
//                       (new Communicate(new Object[]{username},"user.loadByUsername")))).getRecordMetadata().toString());
//
//    }
//
//    public Object allUsers() {
//        return null;
//    }
//
//    public void deleteUser(int userId) {
//        return ;
//    }
}
}
