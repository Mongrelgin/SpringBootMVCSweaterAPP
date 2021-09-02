package com.titan.sweater.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private boolean active;

    //???ElementCollection нужен чтобы Role не создавалась в базе, т.к. это простой Enum
    //fetch - параметр который определеят как значения будут подгружаться
    //Eager(жадный) - при запросе будут подгружаться все роли
    //Lazy(ленивый) - роли будут подгружаться, только когда к ним обращаются
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    //CollectionTable указывает что у нас нет описания для таблицы и типо пусть он сам её создаст с этими данными
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
