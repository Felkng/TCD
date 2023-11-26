package com.br.edu.ifnmg.tcd.librarian;

import java.time.LocalDate;

import com.br.edu.ifnmg.tcd.credential.Credential;
import com.br.edu.ifnmg.tcd.role.Role;
import com.br.edu.ifnmg.tcd.user.User;

public class Librarian extends User {

    public Librarian() {
        super();
    }

    public Librarian(String name, String email, LocalDate birthDate, Role role, Credential credential) throws Exception {
        super(name, email, birthDate, role, credential);
    }

}
