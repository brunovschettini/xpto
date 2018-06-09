package br.com.xptosystems.security.access;

import br.com.xptosystems.security.Users;
import br.com.xptosystems.utils.Sessions;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserControlMB {

    private Users users;
    private final Users usuarioSessao = (Users) Sessions.getObject("sessionUser");

    public String verificaPermissao() {
        if (usuarioSessao != null) {
            if (usuarioSessao.getId() != -1) {

            }
        }
        return "index";
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
