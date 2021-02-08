package io.github.jetmedeiros.gmailserver.controller;

import io.github.jetmedeiros.gmailserver.dto.EmailDTO;
import io.github.jetmedeiros.gmailserver.dto.EmailOnHomeDTO;
import io.github.jetmedeiros.gmailserver.model.Email;
import io.github.jetmedeiros.gmailserver.model.User;
import io.github.jetmedeiros.gmailserver.repository.EmailRepository;
import io.github.jetmedeiros.gmailserver.repository.UserRepository;
import io.github.jetmedeiros.gmailserver.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emails")
@CrossOrigin("http://localhost:4200/**")
public class EmailController {
    @Autowired
    private EmailService service;

    @Autowired
    private EmailRepository repository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Email> find(@PathVariable Integer id){
        Email obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<EmailOnHomeDTO>> findAllToHome(){
        List<Email> list = service.findAll();
        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
        List<EmailOnHomeDTO> listDTO =  list.stream().map(obj ->
                new EmailOnHomeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Email create(@RequestBody EmailDTO objDTO){
       // LocalDate data = LocalDate.parse(objDTO.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idUser = objDTO.getIdUser();
        Integer cc = objDTO.getCc();
        LocalDate date = LocalDate.now();

        Date data =  convertToDateViaSqlDate(date);
        User user =
                userRepository
                        .findById(idUser)
                        .orElseThrow( () ->
                                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente"));

        User userCC =
                userRepository
                        .findById(cc)
                        .orElseThrow( () ->
                                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente"));


        Email email = new Email();
        email.setContent(objDTO.getContent());
        email.setTitle(objDTO.getTitle());
        email.setDate(data);
        email.setUser(user);
        email.setIsread(false);
        email.setCc(userCC);
        email.setFavorite(objDTO.isFavorite());


//        email = service.create(email);
        return repository.save(email);
    }

    @PatchMapping("{id}/favorite")
    public void favorite( @PathVariable Integer id ){
        Optional<Email> email = repository.findById(id);
        email.ifPresent( c -> {
            boolean favorite = c.isFavorite() == Boolean.TRUE;
            c.setFavorite(!favorite);
            repository.save(c);
        });
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




//    @RequestMapping(method=RequestMethod.GET)
//    public ResponseEntity<List<EmailDTO>> findAll(){
//        List<Email> list = service.findAll();
//        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
//        List<EmailDTO> listDTO =  list.stream().map(obj ->
//                new EmailDTO(obj)).collect(Collectors.toList());
//        return ResponseEntity.ok().body(listDTO);
//    }

    @RequestMapping(value = "/page", method=RequestMethod.GET)
    public ResponseEntity<Page<EmailDTO>> findPage(@RequestParam (value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //24 é mais responsivo
                                                     @RequestParam (value = "orderBy", defaultValue = "date") String orderBy,
                                                     @RequestParam (value = "direction", defaultValue = "ASC") String direction){

        Page<Email> list = service.findPage(page, linesPerPage, orderBy, direction);
        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
        Page<EmailDTO> listDTO =  list.map(obj ->
                new EmailDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

//    @RequestMapping(value = "/enviados", method=RequestMethod.GET)
//    public ResponseEntity<Page<Email>> enviados(@RequestParam (value = "page", defaultValue = "0") Integer page,
//                                                   @RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //24 é mais responsivo
//                                                   @RequestParam (value = "orderBy", defaultValue = "date") String orderBy,
//                                                   @RequestParam (value = "direction", defaultValue = "ASC") String direction){
//
//        Page<Email> list = service.enviados(page, linesPerPage, orderBy, direction);
//        //vai percorrer a lista e fazer uma operacao para cada elemento da lista
////        Page<EmailDTO> listDTO =  list.map(obj ->
////                new EmailDTO(obj));
//        return ResponseEntity.ok().body(list);
//    }
}
