package com.br.edu.ifnmg.tcd.reader;

import java.time.LocalDate;

import com.br.edu.ifnmg.tcd.credential.Credential;
import com.br.edu.ifnmg.tcd.role.Role;
import com.br.edu.ifnmg.tcd.user.User;

public class Reader extends User {

    public Reader() {
        super();
    }

    public Reader(String name, String email, LocalDate birthDate, Role role, Credential credential) throws Exception {
        super(name, email, birthDate, role, credential);
    }
}
