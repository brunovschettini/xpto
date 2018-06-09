//package br.com.xptosystems.beans;
//
//import br.com.xptosystems.security.UsersProfile;
//import br.com.xptosystems.dao.Dao;
//import br.com.gestor.person.DocumentType;
//import br.com.gestor.person.Gender;
//import br.com.gestor.person.NaturalPerson;
//import br.com.gestor.security.Client;
//import br.com.xptosystems.security.LevelUser;
//import br.com.gestor.security.SecurityGroup;
//import br.com.gestor.security.SecurityGroupUsers;
//import br.com.xptosystems.security.Users;
//import br.com.xptosystems.security.access.AccessControlMB;
//import br.com.xptosystems.securitys.dao.UsersDao;
//import br.com.gestor.store.dao.SecurityGroupDao;
//import br.com.gestor.store.dao.SecurityGroupUsersDao;
//
//// import br.com.agendafacil.social.dao.SocialMediaDao;
//import br.com.xptosystems.utils.Messages;
//import br.com.xptosystems.utils.Sessions;
//import br.com.xptosystems.utils.Valida;
//import com.ibm.icu.util.GenderInfo.Gender;
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.model.SelectItem;
//import javax.ws.rs.client.Client;
//import org.dom4j.DocumentType;
//
//@ManagedBean
//@SessionScoped
//public class UsersMB implements Serializable {
//
//    private Users users;
//    private UsersProfile profile;
//    private AccessControlMB accessControlMB;
//    private String password;
//    private String passwordConfirm;
//    private String rememberPasswordConfirm;
//    private List<SelectItem> listGenre;
//    private List<SelectItem> listDocumentType;
//    private List<SelectItem> listLevelUser;
//    private List<SecurityGroup> listSecurityGroup;
//    private Long idGenre;
//    private Long idDocumentType;
//    private Long idLevelUser;
//    private boolean disabledRegeneratePassword;
//    private List<Users> listUsers;
//    private String by;
//    private String q;
//
//    public UsersMB() {
//        accessControlMB = (AccessControlMB) Sessions.getObject("accessControlMB");
//
//        listGenre = new ArrayList<>();
//        listLevelUser = new ArrayList<>();
//        listDocumentType = new ArrayList<>();
//        listSecurityGroup = new ArrayList<>();
//        password = "";
//        passwordConfirm = "";
//        disabledRegeneratePassword = true;
//        rememberPasswordConfirm = "";
//        idLevelUser = null;
//        idGenre = null;
//        idDocumentType = new Long(1);
//        users = new Users();
//        profile = new UsersProfile();
//        by = "name";
//        q = "";
//        loadListUsers();
//    }
//
//    public synchronized void saveNewUser() throws NoSuchAlgorithmException {
//        if (users.getPerson().getName().isEmpty()) {
//            Messages.warn("Validação", "INFORMAR SEU NOME");
//            return;
//        }
////        if (users.getPerson().getBirthday() == null) {
////            Messages.warn("Validação", "INFORMAR UMA DATA DE NASCIMENTO VÁLIDA");
////            return;
////        }
//        if (users.getEmail().isEmpty()) {
//            Messages.warn("Validação", "INFORMAR EMAIL!");
//            return;
//        }
//        if (!Valida.email(users.getEmail())) {
//            Messages.fatal("Erro", "E-MAIL INVÁLIDO!");
//            return;
//        }
//        if (users.getPassword().length() < 6) {
//            Messages.warn("Validação", "A SENHA DEVE CONTER NO MÍNIMO 6 CARACTERES");
//            return;
//        }
//        if (!passwordConfirm.equals(users.getPassword())) {
//            Messages.warn("Validação", "SENHAS NÃO CONFEREM");
//            return;
//        }
//        UsersDao usersDao = new UsersDao();
//        if (usersDao.existUserEmail(users.getEmail())) {
//            Messages.warn("Validação", "EMAIL JÁ CADASTRADO NO SISTEMA!");
//            Messages.warn("Validação", "SE VOCÊ ESQUECEU SUA SENHA, DESABILITOU O CADASTRO SOLICITE UMA NOVA SENHA EM ESQUECI MINHA SENHA");
//            return;
//        }
//        Users u = users;
//        u.setEmail(users.getEmail().trim());
//        // SENHA MD5
//        MessageDigest m = MessageDigest.getInstance("MD5");
//        m.update(passwordConfirm.getBytes(), 0, passwordConfirm.length());
//        u.setPassword(new BigInteger(1, m.digest()).toString(16));
//        // CONFIRMACAO ID MD5
//        m.update(u.getEmail().getBytes(), 0, u.getEmail().length());
//        String confirmCode = new BigInteger(1, m.digest()).toString(16);
//        u.setConfirmCode(confirmCode);
//        Dao dao = new Dao();
//        dao.openTransaction();
//        if (u.getId() == null) {
//            Gender gender = new Gender();
//            gender.setId(idGenre);
////            u.getPerson().setGender((Gender) dao.find(gender));
//            if (!dao.save(u.getPerson())) {
//                dao.rollback();
//                Messages.warn("Erro", "CADASTRO NÃO PODE SER CONCLUÍDO, TENTE MAIS TARDE");
//                return;
//            }
//            if (dao.save(u)) {
//                try {
//                    if (!sendMail(u, confirmCode)) {
//                        Messages.warn("Erro", "SERVIÇO DE EMAIL TEMPORARIAMENTE INDISPONÍVEL, TENTE MAIS TARDE");
//                        dao.rollback();
//                        return;
//                    }
//                    dao.commit();
//                    Messages.info("Sucesso", "EM INSTANTES VOCÊ RECEBERÁ UM EMAIL DE CONFIRMAÇÃO DO SEU CADASTRO");
//                    idGenre = null;
//                    passwordConfirm = "";
//                    if (Sessions.exists("linkRedirect")) {
//                        Sessions.remove("linkRedirect");
//
//                    }
//                    users = new Users();
//                    return;
//                } catch (NoSuchAlgorithmException e) {
//                    Messages.error("Erro", "SERVIÇO TEMPORARIAMENTE INDISPONÍVEL");
//                    dao.rollback();
//                }
//            } else {
//                Messages.warn("Suporte", "SOLICITE UM CHAMADO TÉCNICO A NOSSA EQUIPE DE SUPORTE RELATANDO ESTE ERRO");
//                dao.rollback();
//            }
//        }
//        users = new Users();
//        idGenre = null;
//        passwordConfirm = "";
//    }
//
//    public synchronized void save() throws NoSuchAlgorithmException {
//        if (users.getName().isEmpty()) {
//            Messages.warn("Validação", "Informar o nome do usuário!", true);
//            return;
//        }
//        if (users.getId() == null) {
//            if (password.isEmpty()) {
//                Messages.warn("Validação", "Informar uma senha!", true);
//                return;
//            }
//        }
////        if (users.getPerson().getBirthday() == null) {
////            Messages.warn("Validação", "Informar sua data de nascimento válida",true);
////            return;
////        }
//        Dao dao = new Dao();
//        if (!password.isEmpty()) {
//            MessageDigest m = MessageDigest.getInstance("MD5");
//            m.update(password.getBytes(), 0, password.length());
//            users.setPassword(new BigInteger(1, m.digest()).toString(16));
//        }
//        dao.openTransaction();
//        if (findByDocument(users.getPerson().getDocument()) != null) {
//            Messages.warn("Validação", "DOCUMENTO JÁ CADASTRADO!", true);
//            return;
//        }
//        if (users.getId() == null) {
//            UsersDao usersDao = new UsersDao();
//            if (usersDao.existUserEmail(users.getEmail()) || usersDao.existUserNickname(users.getEmail())) {
//                Messages.warn("Validação", "EMAIL/APELIDO JÁ EXISTE!", true);
//                Messages.warn("Validação", "SE VOCÊ ESQUECEU SUA SENHA, DESABILITOU O CADASTRO SOLICITE UMA NOVA SENHA EM ESQUECI MINHA SENHA", true);
//                return;
//            }
//            users.getPerson().setDocumentType((DocumentType) dao.find(new DocumentType(), idDocumentType));
//            users.setLevelUser((LevelUser) dao.find(new LevelUser(), idLevelUser));
//            users.getPerson().setClient((Client) Sessions.getObject("sessionClient"));
//            users.setClient((Client) Sessions.getObject("sessionClient"));
//            if (!dao.save(users.getPerson())) {
//                dao.rollback();
//                Messages.warn("Erro", "Ao registrar pessoa!", true);
//                return;
//            }
//            NaturalPerson np = new NaturalPerson();
//            np.setGender((Gender) dao.find(new Gender(), idGenre));
//            np.setClient(Client.get());
//            np.setPerson(users.getPerson());
//            if (!dao.save(np)) {
//                dao.rollback();
//                Messages.warn("Erro", "Ao registrar pessoa física!", true);
//                return;
//            }
//            if (!dao.save(users)) {
//                dao.rollback();
//                Messages.warn("Erro", "Ao registrar usuário!", true);
//                return;
//            }
//            profile.setUsers(users);
//            if (!dao.save(profile)) {
//                dao.rollback();
//                Messages.warn("Erro", "Ao registrar perfil!", true);
//                return;
//            }
//            Messages.info("Sucesso", "Registro inserido.", true);
//            dao.commit();
//            loadListUsers();
//            loadListSecurityGroup();
//        } else if (users.getId() != null) {
////            users.getPerson().setGender((Gender) dao.find(new Gender(), idGenre));
//            users.getPerson().setDocumentType((DocumentType) dao.find(new DocumentType(), idDocumentType));
//            users.setLevelUser((LevelUser) dao.find(new LevelUser(), idLevelUser));
//            if (!dao.update(users.getPerson())) {
//                dao.rollback();
//                Messages.warn("Erro", "Ao atualizar pessoa!", true);
//                return;
//            }
//            if (!dao.update(users)) {
//                dao.rollback();
//                Messages.warn("Erro", "Ao atualizar usuário!", true);
//                return;
//            }
//            if (profile != null && profile.getId() != null) {
//                if (!dao.update(profile)) {
//                    dao.rollback();
//                    Messages.warn("Erro", "Ao registrar perfil!", true);
//                    return;
//                }
//            }
//            Messages.info("Sucesso", "Cadastro atualizado.", true);
//            dao.commit();
//            loadListUsers();
//        }
//        password = "";
//    }
//
//    public synchronized void rememberPassword() throws NoSuchAlgorithmException {
//
//    }
//
//    public synchronized void validadeCodeConfirmed() {
//        users = new Users();
//        UsersDao usersDao = new UsersDao();
//        Users u = usersDao.findCodeConfirmed(rememberPasswordConfirm);
//        if (u != null) {
//            disabledRegeneratePassword = false;
//        }
//    }
//
//    public synchronized void saveNewPassword() throws NoSuchAlgorithmException {
//        if (users.getPassword().length() < 6) {
//            Messages.warn("Validação", "A SENHA DEVE CONTER NO MÍNIMO 6 CARACTERES");
//            return;
//        }
//        if (!passwordConfirm.equals(users.getPassword())) {
//            Messages.warn("Validação", "SENHAS NÃO CONFEREM");
//            return;
//        }
//        UsersDao usersDao = new UsersDao();
//        Users u = usersDao.findCodeConfirmed(rememberPasswordConfirm);
//        Dao dao = new Dao();
//        if (u != null) {
//            MessageDigest m = MessageDigest.getInstance("MD5");
//            m.update(passwordConfirm.getBytes(), 0, passwordConfirm.length());
//            u.setPassword(new BigInteger(1, m.digest()).toString(16));
//            m.update(("" + new Date()).getBytes(), 0, u.getEmail().length());
//            String confirmCode = new BigInteger(1, m.digest()).toString(16);
//            u.setActive(false);
//            u.setConfirmCode(confirmCode);
////            if (!sendMail(u, confirmCode)) {
////                Messages.warn("Erro", "SERVIÇO DE EMAIL TEMPORARIAMENTE INDISPONÍVEL, TENTA NOVAMENTE MAIS TARDE");
////                return;
////            }
//            if (dao.update(u, true)) {
//                Messages.info("Sucesso", "SENHA ATUALIZADA, VOCÊ RECEBERÁ UM EMAIL DE CONFIRMAÇÃO");
//            } else {
//                Messages.warn("Erro", "SERVIÇO DE EMAIL TEMPORARIAMENTE INDISPONÍVEL, TENTE NOVAMENTE MAIS TARDE");
//            }
//            users = new Users();
//            idGenre = null;
//            passwordConfirm = "";
//            disabledRegeneratePassword = true;
//            rememberPasswordConfirm = "";
//        }
//    }
//
//    public boolean sendMail(Users u, String confirmCode) throws NoSuchAlgorithmException {
//        return false;
//    }
//
//    public synchronized String delete() {
//        Dao dao = new Dao();
//        users.setActive(false);
//        users.setConfirmCode(null);
//        if (dao.update(users, true)) {
//            Messages.info("Sucesso", "USUÁRIO INATIVADO", true);
//        }
//        return null;
//
//    }
//
//    public Users getUsers() {
//        return users;
//    }
//
//    public void setUsers(Users users) {
//        this.users = users;
//    }
//
//    public String getPasswordConfirm() {
//        return passwordConfirm;
//    }
//
//    public void setPasswordConfirm(String passwordConfirm) {
//        this.passwordConfirm = passwordConfirm;
//    }
//
//    public List<SelectItem> getListGenre() {
//        if (listGenre.isEmpty()) {
//            List<Gender> list = new Dao().list(new Gender());
//            for (int i = 0; i < list.size(); i++) {
//                listGenre.add(new SelectItem(list.get(i).getId(), list.get(i).getName(), "" + list.get(i).getId()));
//            }
//            idGenre = new Long(3);
//        }
//        return listGenre;
//    }
//
//    public void setListGenre(List<SelectItem> listGenre) {
//        this.listGenre = listGenre;
//    }
//
//    public List<SelectItem> getListDocumentType() {
//        if (listDocumentType.isEmpty()) {
//            listDocumentType = new ArrayList();
//            List<DocumentType> list = new Dao().list(new DocumentType());
//            for (int i = 0; i < list.size(); i++) {
//                listDocumentType.add(new SelectItem(list.get(i).getId(), list.get(i).getName(), "" + list.get(i).getId()));
//            }
//            idDocumentType = new Long(1);
//        }
//        return listDocumentType;
//    }
//
//    public void setListDocumentType(List<SelectItem> listDocumentType) {
//        this.listDocumentType = listDocumentType;
//    }
//
//    public List<SelectItem> getListLevelUser() {
//        if (listLevelUser.isEmpty()) {
//            List<LevelUser> list = new Dao().list(new LevelUser());
//            for (int i = 0; i < list.size(); i++) {
//                listLevelUser.add(new SelectItem(list.get(i).getId(), list.get(i).getName(), "" + list.get(i).getId()));
//            }
//            idLevelUser = new Long(1);
//        }
//        return listLevelUser;
//    }
//
//    public void setListLevelUser(List<SelectItem> listLevelUser) {
//        this.listLevelUser = listLevelUser;
//    }
//
//    public Long getIdGenre() {
//        return idGenre;
//    }
//
//    public void setIdGenre(Long idGenre) {
//        this.idGenre = idGenre;
//    }
//
//    public String getRememberPasswordConfirm() {
//        return rememberPasswordConfirm;
//    }
//
//    public void setRememberPasswordConfirm(String rememberPasswordConfirm) {
//        this.rememberPasswordConfirm = rememberPasswordConfirm;
//    }
//
//    public boolean isDisabledRegeneratePassword() {
//        return disabledRegeneratePassword;
//    }
//
//    public void setDisabledRegeneratePassword(boolean disabledRegeneratePassword) {
//        this.disabledRegeneratePassword = disabledRegeneratePassword;
//    }
//
//    public UsersProfile getProfile() {
//        return profile;
//    }
//
//    public void setProfile(UsersProfile profile) {
//        this.profile = profile;
//    }
//
//    public Long getIdDocumentType() {
//        return idDocumentType;
//    }
//
//    public void setIdDocumentType(Long idDocumentType) {
//        this.idDocumentType = idDocumentType;
//    }
//
//    public Long getIdLevelUser() {
//        return idLevelUser;
//    }
//
//    public void setIdLevelUser(Long idLevelUser) {
//        this.idLevelUser = idLevelUser;
//    }
//
//    public String getMaskDocumentType() {
//        if (null != idDocumentType) {
//            if (idDocumentType == 1) {
//            }
//            if (idDocumentType == 2) {
//                return "999.999.999-99";
//            }
//            if (idDocumentType == 3) {
//            }
//            if (idDocumentType == 4) {
//                return "99.999.999/9999-99";
//            }
//            return "";
//        }
//        return "";
//    }
//
//    public List<Users> getListUsers() {
//        return listUsers;
//    }
//
//    public void setListUsers(List<Users> listUsers) {
//        this.listUsers = listUsers;
//    }
//
//    public final void loadListUsers() {
//        listUsers = new ArrayList();
//        listUsers = new UsersDao().findByClient(((Client) Sessions.getObject("sessionClient")).getId(), by, q);
//    }
//    
//    public final void loadListSecurityGroup() {
//        listSecurityGroup = new ArrayList();
//        SecurityGroupUsersDao sgud = new SecurityGroupUsersDao();
//        if(users.getId() != null) {
//            listSecurityGroup = new SecurityGroupDao().findAvailable(Client.get().getId(), users.getId());
//            for(int i = 0; i < listSecurityGroup.size(); i++) {
//                SecurityGroupUsers sgu = new SecurityGroupUsers();
//                if(sgud.find(users.getId(), listSecurityGroup.get(i).getId()) != null) {
//                    listSecurityGroup.get(i).setSelected(true);
//                }
//            }
//        }
//    }
//    
//    public void change(SecurityGroup sg) {
//        try {
//            SecurityGroupUsers sgu = new SecurityGroupUsers();
//            if(sg.getSelected()) {
//                sgu.setUser(users);
//                sgu.setSecurityGroup(sg);
//                sgu.setClient(Client.get());
//                new Dao().save(sgu, true);
//            } else {
//                new Dao().delete(new SecurityGroupUsersDao().find(users.getId(), sg.getId()), true);
//            }
//        } catch (Exception e) {
//            
//        }
//    }
//    
//
//    public void clear() {
//        Sessions.remove("usersMB");
//    }
//
//    public String edit(Users u) {
//        getListDocumentType();
//        getListGenre();
//        getListLevelUser();
//        users = u;
//        loadListSecurityGroup();
//        idDocumentType = users.getPerson().getDocumentType().getId();
////        idGenre = users.getPerson().getGender().getId();
//        idLevelUser = users.getLevelUser().getId();
////        UsersProfileDao profileDao = new UsersProfileDao();
////        profile = profileDao.findProfileByUser(users.getId());
////        if (profile == null) {
////            profile = new UsersProfile();
////        }
//        return "user";
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public AccessControlMB getAccessControlMB() {
//        return accessControlMB;
//    }
//
//    public void setAccessControlMB(AccessControlMB accessControlMB) {
//        this.accessControlMB = accessControlMB;
//    }
//
//    public String getBy() {
//        return by;
//    }
//
//    public void setBy(String by) {
//        this.by = by;
//    }
//
//    public String getQ() {
//        return q;
//    }
//
//    public void setQ(String q) {
//        this.q = q;
//    }
//
//    public void findByDocument() {
//        Users u = findByDocument(null);
//        if (u != null) {
//            users = u;
//        }
//    }
//
//    public Users findByDocument(String document) {
//        if (document == null) {
//            if (users.getId() == null) {
//                if (!users.getPerson().getDocument().isEmpty()) {
//                    users = new UsersDao().findByDocument(((Client) Sessions.getObject("sessionClient")).getId(), users.getPerson().getDocument());
//                    if (users == null) {
//                        users = new Users();
//                    } else {
//                        idDocumentType = users.getPerson().getDocumentType().getId();
////                        idGenre = users.getPerson().getGender().getId();
//                        idLevelUser = users.getLevelUser().getId();
//
//                    }
//                }
//            }
//        } else {
//            if (!document.isEmpty()) {
//                Users u = new UsersDao().findByDocument(((Client) Sessions.getObject("sessionClient")).getId(), document);
//                if (u != null) {
//                    if (u.getPerson().getDocumentType().getId().equals(2) || u.getPerson().getDocumentType().getId().equals(4)) {
//                        return u;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    public List<SecurityGroup> getListSecurityGroup() {
//        return listSecurityGroup;
//    }
//
//    public void setListSecurityGroup(List<SecurityGroup> listSecurityGroup) {
//        this.listSecurityGroup = listSecurityGroup;
//    }
//
//}
