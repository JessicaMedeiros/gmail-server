package io.github.jetmedeiros.gmailserver.service;

import io.github.jetmedeiros.gmailserver.model.Email;
import io.github.jetmedeiros.gmailserver.repository.EmailRepository;
import io.github.jetmedeiros.gmailserver.repository.UserRepository;
import io.github.jetmedeiros.gmailserver.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Email find(Integer id){
        Optional<Email> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Email.class.getName()));
    }

    public Email create(Email obj) {
        obj.setId(null);
        return  repository.save(obj);
    }

    public void delete(Integer id) {
            repository.deleteById(id);
    }

    public List<Email> findAll() {
        return repository.findAll();
    }

    public List<Email> findAllToHome() {
        return repository.findAll();
    }

    public Page<Email> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

//    public Page<Email> page(Integer page, Integer linesPerPage, String orderBy, String direction){
//        UserSS userSS = UserAuthentication.authenticated();
//        if(userSS == null){
//            throw new AuthorizationException("Acesso Negado");
//        }
//        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
//        User user = userService.find(userSS.getId());
//        return repository.findByIdUser(user, pageRequest);
//
//    }
//
//    public Page<Email> enviados(Integer page, Integer linesPerPage, String orderBy, String direction){
//        UserSS userSS = UserAuthentication.authenticated();
//        if(userSS == null){
//            throw new AuthorizationException("Acesso Negado");
//        }
//        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
//        User user = userService.find(userSS.getId());
//        return repository.findByIdUser(userSS.getId(), pageRequest);
//
//    }

//    public Email fromDTO(EmailDTO dto){
//        return new Email(dto.getId(), dto.getContent(), dto.getTitle(), dto.isIsread(), dto.getIdUser());
//    }


}
