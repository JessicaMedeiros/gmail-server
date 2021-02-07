package io.github.jetmedeiros.gmailserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jetmedeiros.gmailserver.model.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Email> emails = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cc")
    private List<Email> emailsCC = new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        addPerfil(Perfil.CLIENTE);

    }
}
