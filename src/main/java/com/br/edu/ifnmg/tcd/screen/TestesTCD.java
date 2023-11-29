/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.edu.ifnmg.tcd.screen;

import com.br.edu.ifnmg.tcd.credential.Credential;
import com.br.edu.ifnmg.tcd.credential.CredentialDao;
import com.br.edu.ifnmg.tcd.librarian.Librarian;
import com.br.edu.ifnmg.tcd.librarian.LibrarianDao;
import com.br.edu.ifnmg.tcd.reader.Reader;
import com.br.edu.ifnmg.tcd.reader.ReaderDao;
import com.br.edu.ifnmg.tcd.role.Role;
import com.br.edu.ifnmg.tcd.role.RoleDao;
import com.br.edu.ifnmg.tcd.user.User;
import com.br.edu.ifnmg.tcd.user.UserDao;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Felkng <&it;felkng374 at @gmail.com&gt;>
 */
public class TestesTCD {

    public static void criarAdmin() {
        ArrayList<User> x = new ArrayList<>();
        x = new UserDao().findAll();
//        if (x == null) { //Só cria usuário padrão caso não haja nenhum usuário no banco de dados
            Credential credential = new Credential();
            Role role = new Role();

            User admin = null;
            try {
                credential.setUsername("admin");
                credential.setPassword("123");
                credential.setLastAccess(LocalDate.now());
                role.setName("administrador");
                Long roleId = new RoleDao().saveOrUpdate(role);
                role.setId(roleId);
                admin = new User("usuario1", "admin@mail.com", LocalDate.now(), role, credential);
                credential.setUser(admin);
                Long userId = new UserDao().saveOrUpdate(admin);
                admin.setId(-userId);
                credential.setId(userId);
                new CredentialDao().saveOrUpdate(credential);
            } catch (Exception e) {
                System.out.println("Error main: " + e.getMessage());
            }
//        }
    }

    public static void criarLibrarian() {
        try {
            Role role = new Role("Bibliotecario");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);

            Credential credential = new Credential(null, "biblio1", "asdf", LocalDate.now(), null, null);

            Librarian librarian = null;
            librarian = new Librarian(
                    "Usuario2",
                    "bibliotecario@mail.com",
                    LocalDate.now(),
                    role,
                    credential);

            credential.setUser(librarian);
            new LibrarianDao().saveOrUpdate(librarian);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void criarReader() {
        try {
            Role role = new Role("Leitor");
            Long roleID = new RoleDao().saveOrUpdate(role);
            role.setId(roleID);

            Reader reader = null;
            reader = new Reader(
                    "Usuario3",
                    "librarian@mail.com",
                    LocalDate.now(),
                    role,
                    new Credential(null, "leitor1", "qwerty", LocalDate.now(), true, reader));
            reader.getCredential().setUser(reader);
            Long userId = new ReaderDao().saveOrUpdate(reader);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
