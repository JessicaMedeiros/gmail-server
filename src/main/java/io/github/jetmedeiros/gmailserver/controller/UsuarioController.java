package io.github.jetmedeiros.gmailserver.controller;

import io.github.jetmedeiros.gmailserver.dao.EmailDTO;
import io.github.jetmedeiros.gmailserver.dao.UserDTO;
import io.github.jetmedeiros.gmailserver.model.Email;
import io.github.jetmedeiros.gmailserver.model.User;
import io.github.jetmedeiros.gmailserver.repository.UserRepository;
import io.github.jetmedeiros.gmailserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<User> find(@PathVariable Integer id){
        User obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return repository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody UserDTO updatedObj){
        User cat = service.fromDTO(updatedObj);
        cat.setId(id);
        cat = service.update(cat);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
        List<UserDTO> listDTO =  list.stream().map(obj ->
                new UserDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

//    @RequestMapping(value = "/page", method=RequestMethod.GET)
//    public ResponseEntity<Page<UsuarioDTO>> findPage(@RequestParam (value = "page", defaultValue = "0") Integer page,
//                                                     @RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //24 é mais responsivo
//                                                     @RequestParam (value = "orderBy", defaultValue = "name") String orderBy,
//                                                     @RequestParam (value = "direction", defaultValue = "ASC") String direction){
//
//        Page<Category> list = service.findPage(page, linesPerPage, orderBy, direction);
//        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
//        Page<CategoryDTO> listDTO =  list.map(obj ->
//                new CategoryDTO(obj));
//        return ResponseEntity.ok().body(listDTO);
//    }
}
