package io.github.jetmedeiros.gmailserver.service;


import io.github.jetmedeiros.gmailserver.config.UserSS;
import io.github.jetmedeiros.gmailserver.dao.UserDTO;
import io.github.jetmedeiros.gmailserver.model.User;
import io.github.jetmedeiros.gmailserver.model.enums.Perfil;
import io.github.jetmedeiros.gmailserver.repository.UserRepository;
import io.github.jetmedeiros.gmailserver.service.exception.AuthorizationException;
import io.github.jetmedeiros.gmailserver.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UserRepository repository;


    @Transactional
    public User insert(User obj) {
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }



    public User find(Integer id) {

        UserSS user = UserAuthentication.authenticated();
//        if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//            throw new AuthorizationException("Acesso negado");
//        }

        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = repository
                .findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("User not found"));

        return new UserSS(user.getId(), user.getUsername(), user.getPassword(), user.getPerfis());
//
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles("USER")
//                .build()
//                ;
    }

    public User fromDTO(UserDTO dto) {
        User user = new User(null, dto.getUsername(),  pe.encode(dto.getPassword()));
        return user;
    }


}
