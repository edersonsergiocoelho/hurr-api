package br.com.escconsulting.repositories;

import br.com.escconsulting.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements User2Repository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO hurr.user (id, login, password, role) VALUES(?,?,?,?)",
                new Object[] { user.getId(), user.getLogin(), user.getPassword(), user.getRole().getRole() });
    }
}
