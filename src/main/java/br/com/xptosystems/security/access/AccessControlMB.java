//package br.com.xptosystems.security.access;
//
//import br.com.xptosystems.dao.Dao;
//import br.com.gestor.financial.FinancialGroupClient;
//import br.com.gestor.financial.FinancialGroupRoutine;
//import br.com.gestor.financial.dao.FinancialGroupClientDao;
//import br.com.gestor.financial.dao.FinancialGroupRoutineDao;
//import br.com.gestor.security.Client;
//import br.com.gestor.security.ClientModules;
//import br.com.xptosystems.security.Routines;
//import br.com.gestor.security.SecurityGroupRoutines;
//import br.com.gestor.security.SecurityGroupRoutinesDao;
//import br.com.gestor.security.SecurityGroupUsers;
//import br.com.xptosystems.security.Users;
//import br.com.xptosystems.securitys.dao.ClientModulesDao;
//import br.com.xptosystems.securitys.dao.RoutinesDao;
//import br.com.xptosystems.securitys.dao.UsersDao;
//import br.com.gestor.store.dao.SecurityGroupUsersDao;
//import br.com.xptosystems.utils.Messages;
//import br.com.xptosystems.utils.Sessions;
//import java.io.IOException;
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.net.MalformedURLException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.List;
////import javax.enterprise.context.SessionScoped;
//import javax.faces.application.NavigationHandler;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//
//import javax.servlet.RequestDispatcher;
////import javax.inject.Inject;
////import javax.inject.Named;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@ManagedBean
//@SessionScoped
//public class AccessControlMB implements Serializable {
//
//    //@Inject
//    private Users user;
//    private HttpServletRequest paginaRequerida;
//    private String urlDestino;
//    private String linkFacebook;
//    //private LoginFacebook loginFacebook;
//    private String login;
//    private String password;
//    private boolean rememberMe;
//    private String CONTEXT = "gestor/";
//    private String client;
//    private SecurityGroupRoutines sgr;
//
//    public AccessControlMB() {
//        sgr = null;
//        client = null;
//        user = new Users();
//        urlDestino = "";
//        linkFacebook = "";
//        login = "";
//        password = "";
//    }
//
////    @PreDestroy
////    public void destroy() {
////        //Sessions.remove("accessControlMB");
////    }
//    public synchronized String authUsers() throws NoSuchAlgorithmException, IOException {
//        if (login.isEmpty()) {
//            Messages.info("Validação", "DIGITE UM EMAIL OU APELIDO!", true);
//            return null;
//        }
//        user = new Users();
//        user.setEmail(login.trim());
////        if (!Valida.email(login.trim())) {
////            Messages.info("Validação", "DIGITE UM EMAIL VÁLIDO!");
////            return null;
////        }
//        if (password.isEmpty()) {
//            Messages.info("Validação", "DIGITE UMA SENHA", true);
//            return null;
//        }
//        UsersDao userDao = new UsersDao();
//        Users u = new Users();
//        u.setEmail(login);
//        MessageDigest m = MessageDigest.getInstance("MD5");
//        m.update(password.getBytes(), 0, password.length());
//        password = new BigInteger(1, m.digest()).toString(16);
//        u.setPassword(password);
//        u = userDao.authUser(u);
//        if (u == null) {
//            password = "";
//            user = new Users();
//            Messages.warn("Validação", "USUÁRIO / SENHA INVÁLIDOS!", true);
//            return null;
//        }
//        if (!u.isActive()) {
//            password = "";
//            Messages.warn("Validação", "Seu cadastro encontra-se inátivo!", true);
//            Messages.warn("-", "!Solicite uma nova senha e reative seu cadastro.", true);
//            return null;
//        }
//        if (Sessions.exists("linkRedirect")) {
//            Dao dao = new Dao();
//            Sessions.remove("linkRedirect");
//
//        }
//        Sessions.put("sessionUsers", u);
//        Sessions.put("userName", u.getPerson().getName());
//        Sessions.put("sessionClient", u.getClient());
//        Sessions.put("sessionClientModules", new ClientModulesDao().findByClient(Client.get().getId()));
//        Sessions.put("sessionFinancialGroupClient", new FinancialGroupClientDao().findAllByClient(Client.get().getId()));
//        Sessions.put("sessionFinancialGroupRoutine", new FinancialGroupRoutineDao().findByClient(Client.get().getId()));
//        Sessions.put("sessionSGU", new SecurityGroupUsersDao().findAllBy(Users.get().getId()));
//
//        // GERAR O COCKIE DA SESSÃO
//        if (rememberMe) {
//            try {
//                //FacesContext
//                FacesContext facesContext = FacesContext.getCurrentInstance();
//                //Cria cookie
//                Cookie[] cookie = new Cookie[3];
//                cookie[0] = new Cookie("us3r5l0g1ns4l40f3c1il", "" + u.getEmail());
//                cookie[1] = new Cookie("us3r5p4ssw0rds4l40f3c1il", "" + u.getPassword());
//                cookie[2] = new Cookie("us3r51sr3m3mb3rs4l40f3c1il", "" + 1);
//                cookie[0].setMaxAge(-1);
//                cookie[1].setMaxAge(-1);
//                cookie[2].setMaxAge(-1);
//                //Adiciona
//                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie[0]);
//                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie[1]);
//                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie[2]);
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        }
//        user = u;
//        login = "";
//        password = "";
//        return "dashboard.xhtml?faces-redirect=true";
//    }
//
//    public void validSessions() throws IOException {
//        if (!Sessions.exists("sessionUsers")) {
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//            try {
//                //obtem a lista de cookies
//                Cookie[] cookies = request.getCookies();
//                //foreach
//                String loginString = "";
//                String passwordString = "";
//                String active = "";
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().trim().equals("us3r5l0g1ns4l40f3c1il")) {
//                        loginString = cookie.getValue();
//                    } else if (cookie.getName().trim().equals("us3r5p4ssw0rdfut0nl1n3")) {
//                        passwordString = cookie.getValue();
//                    } else if (cookie.getName().trim().equals("us3r51sr3m3mb3rfut0nl1n3")) {
//                        active = cookie.getValue();
//                    }
//                }
//                if (active != null && !active.equals("") && active.equals("1")) {
//                    if ((loginString != null && !loginString.equals("")) && (passwordString != null && !passwordString.equals(""))) {
//                        Users u = new Users();
//                        u.setEmail(loginString);
//                        u.setPassword(passwordString);
//                        UsersDao userDao = new UsersDao();
//                        u = userDao.authUser(u);
//                        if (u == null) {
//                            user = new Users();
//                            Messages.warn("Validação", "Usuário / senha inválidos!");
//                            return;
//                        }
//                        if (!u.getActive()) {
//                            Messages.warn("Validação", "Seu cadastro encontra-se inátivo!");
//                            Messages.warn("-", "!Solicite uma nova senha e reative seu cadastro.");
//                            return;
//                        }
//                        if (Sessions.exists("linkRedirect")) {
//                            Dao dao = new Dao();
//                            Sessions.remove("linkRedirect");
//
//                        }
//                        Sessions.put("sessionUsers", u);
//                        Sessions.put("userName", u.getPerson().getName());
//                        user = u;
//                    }
//                }
//                if (!Sessions.exists("sessionUsers")) {
//                    NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//                    nh.handleNavigation(facesContext, null, "login");
//                }
//            } catch (Exception e) {
//                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//                nh.handleNavigation(facesContext, null, "login");
//            }
//        }
//        // CAPTURAR COCKIE
//    }
//
//    public synchronized String logout() throws IOException {
//        //Destroy cookie
//        Cookie[] cookie = new Cookie[3];
//        cookie[0] = new Cookie("us3r5l0g1ns4l40f3c1il", null);
//        cookie[1] = new Cookie("us3r5p4ssw0rds4l40f4c1l", null);
//        cookie[2] = new Cookie("us3r51sr3m3mb3rs4l40f4c1l", "" + 0);
//        cookie[0].setMaxAge(-1);
//        cookie[1].setMaxAge(-1);
//        cookie[2].setMaxAge(-1);
//        //Adiciona
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie[0]);
//        ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie[1]);
//        ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(cookie[2]);
//        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
//        session.invalidate();
////        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
////        nh.handleNavigation(facesContext, null, "index");
//        return "login.xhtml?faces-redirect=true";
//    }
//
//    public Users getUsers() {
//        if (user == null || user.getId() == null) {
//            if (Sessions.exists("sessionUsers")) {
//                user = (Users) Sessions.getObject("sessionUsers");
//            }
//        }
//        return user;
//    }
//
//    public void setUsers(Users user) {
//        this.user = user;
//    }
//
//    public static Users getStaticSessionUsers() {
//        if (!Sessions.exists("sessionUsers")) {
//            return (Users) Sessions.getObject("sessionUsers");
//        }
//        return null;
//    }
//
//    public Users getSessionUsers() {
//        if (Sessions.exists("sessionUsers")) {
//            return (Users) Sessions.getObject("sessionUsers");
//        }
//        return null;
//    }
//
//    /**
//     * Método que chama URL do facebook onde o usuário poderá autorizar a
//     * aplicação a acessar seus recursos privados.
//     *
//     * @return
//     * @throws java.io.IOException
//     * @throws java.net.MalformedURLException
//     * @throws org.primefaces.json.JSONException
//     */
//    private String convertToPage(String currentPage) {
//        currentPage = currentPage.substring(currentPage.lastIndexOf("/") + 1, currentPage.lastIndexOf("."));
//        return currentPage;
//    }
//
//    public String getAllowed() throws IOException {
//        reloadUser();
//        try {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            HttpServletRequest hsr = (HttpServletRequest) fc.getExternalContext().getRequest();
//            Users currentUser = (Users) Sessions.getObject("sessionUsers");
//            if (currentUser == null) {
//                redirect("error_401");
//                return null;
//            }
//            String currentPage = convertToPage(hsr.getRequestURI());
//            if (currentPage.isEmpty()) {
//                return null;
//            }
//            RoutinesDao routinesDao = new RoutinesDao();
//            Routines r = routinesDao.findRoutinesByPage(currentPage);
//            if (!renderedRoutineStatic(r.getId())) {
//                redirect("error_403");
//                return null;
//            }
//            NavigationHandler nh = fc.getApplication().getNavigationHandler();
//            if (r != null) {
//                if (r.isPrivatePage() || (currentUser != null && currentPage.equals("index"))) {
//                    if (currentUser == null) {
//                        redirect("error_404");
//                        return null;
//                    } else {
////                        UserPlansDao userPlansDao = new UserPlansDao();
////                        UserPlans userPlans = userPlansDao.findUserPlansByUser(((Users) Sessions.getObject("sessionUsers")).getId());
////                        if (userPlans == null && !currentPage.equals("user_plans")) {
////                            redirect("user_plans");
////                            return null;
////                        }
//                    }
//                    if (currentUser.getId().equals(new Long(1)) || currentUser.getLevelUser().getNumberLevel().equals(6)) {
//                        Sessions.put("sessionRoutine", r);
//                    } else {
//                        r = Routines.get();
//                        if (!r.getActions().equals("dashboard")) {
//                            List<SecurityGroupUsers> sgu = new SecurityGroupUsers().getListSecurityGroupUsers();
//                            if (sgu.isEmpty()) {
//                                redirect("error_403");
//                                return null;
//                            }
//                            for (int i = 0; i < sgu.size(); i++) {
//                                SecurityGroupRoutines sgr = new SecurityGroupRoutinesDao().find(Client.get().getId(), sgu.get(i).getSecurityGroup().getId(), r.getId());
//                                if (sgr == null) {
//                                    redirect("error_403");
//                                    return null;
//                                }
//                                if (!sgr.isR()) {
//                                    redirect("error_403");
//                                    return null;
//                                }
//
//                            }
//                            Sessions.put("sessionRoutine", r);
//                        }
//                    }
//                }
//            } else if (!currentPage.equals("error_404")) {
//                redirect("error_404");
//                return null;
//            }
//
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }
//
//    public void redirect(String page) throws IOException {
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/" + CONTEXT + page + ".xhtml");
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//            // ec.redirect(ec.getRequestContextPath() + "/" + page + ".jsf");   
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            // response.sendRedirect(ec.getRequestContextPath() + "/" + page + ".jsf");
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.getApplication().getNavigationHandler().handleNavigation(context, null, ec.getRequestContextPath() + "/" + page + ".xhtml");
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            RequestDispatcher dd = request.getRequestDispatcher(page + ".jsf");
//            dd.forward(request, response);
//
//        } catch (Exception e) {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect(page + ".xhtml");
//            } catch (IOException e2) {
//                return;
//            }
//        }
//    }
//
//    public void setLinkFacebook(String linkFacebook) {
//        this.linkFacebook = linkFacebook;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
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
//    public boolean isRememberMe() {
//        return rememberMe;
//    }
//
//    public void setRememberMe(boolean rememberMe) {
//        this.rememberMe = rememberMe;
//    }
//
//    public void reloadUser() {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//        Object currentUser = session.getAttribute("sessionUsers");
//        if (currentUser == null) {
//            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//            try {
//                //obtem a lista de cookies
//                Cookie[] cookies = request.getCookies();
//                //foreach
//                String login = "";
//                String password = "";
//                String active = "";
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().trim().equals("us3r5l0g1ns4l40f3c1il")) {
//                        login = cookie.getValue();
//                    } else if (cookie.getName().trim().equals("us3r5p4ssw0rdfut0nl1n3")) {
//                        password = cookie.getValue();
//                    } else if (cookie.getName().trim().equals("us3r51sr3m3mb3rfut0nl1n3")) {
//                        active = cookie.getValue();
//                    }
//                }
//                if (active != null && !active.equals("") && active.equals("1")) {
//                    if ((login != null && !login.equals("")) && (password != null && !password.equals(""))) {
//                        Users u = new Users();
//                        u.setEmail(login);
//                        u.setPassword(password);
//                        UsersDao userDao = new UsersDao();
//                        u = userDao.authUser(u);
//                        if (u == null) {
//                            Messages.warn("Validação", "Usuário / senha inválidos!");
//                            return;
//                        }
//                        if (!u.isActive()) {
//                            Messages.warn("Validação", "Seu cadastro encontra-se inátivo!");
//                            Messages.warn("-", "!Solicite uma nova senha e reative seu cadastro.");
//                            return;
//                        }
//                        if (Sessions.exists("linkRedirect")) {
//                            Dao dao = new Dao();
//                            Sessions.remove("linkRedirect");
//                        }
//                        Sessions.put("sessionUsers", u);
//                        Sessions.put("userName", u.getPerson().getName());
//                    }
//                }
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        }
//    }
//
//    public Client getSessionClient() {
//        if (Sessions.exists("sessionClient")) {
//            return (Client) Sessions.getObject("sessionClient");
//        }
//        return null;
//    }
//
//    public Boolean renderedModule(Long module_id) {
//        return renderedModuleStatic(module_id);
//    }
//
//    public static Boolean renderedModuleStatic(Long module_id) {
//        if (Client.get() == null) {
//            return false;
//        }
//        if (Client.get().getId().equals(new Long(1))) {
//            return true;
//        }
//        if (Sessions.exists("sessionClientModules")) {
//            List<ClientModules> list = (List<ClientModules>) Sessions.getList("sessionClientModules");
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getModules().getId().equals(module_id)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public Boolean renderedFinancialGroupClint(Long financial_group_id) {
//        return renderedFinancialGroupClintStatic(financial_group_id);
//    }
//
//    public static Boolean renderedFinancialGroupClintStatic(Long financial_group_id) {
//        if (Client.get() == null) {
//            return false;
//        }
//        if (Client.get().getId().equals(new Long(1))) {
//            return true;
//        }
//        if (Sessions.exists("sessionFinancialGroupClient")) {
//            List<FinancialGroupClient> list = (List<FinancialGroupClient>) Sessions.getList("sessionFinancialGroupClient");
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getFinancialGroup().getId().equals(financial_group_id)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public Boolean renderedRoutine(Long routine_id) {
//        return renderedRoutineStatic(routine_id);
//    }
//
//    public static Boolean renderedRoutineStatic(Long routine_id) {
//        if (Client.get() == null) {
//            return false;
//        }
//        if (Client.get().getId().equals(new Long(1))) {
//            return true;
//        }
//        if (!getMasterClientStatic()) {
//            if (Sessions.exists("sessionFinancialGroupRoutine")) {
//                List<FinancialGroupRoutine> list = (List<FinancialGroupRoutine>) Sessions.getList("sessionFinancialGroupRoutine");
//                if (list.isEmpty()) {
//                    return false;
//                }
//                for (int i = 0; i < list.size(); i++) {
//                    if (list.get(i).getRoutine().getId().equals(routine_id)) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public String getClient() {
//        if (client == null) {
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            String p1 = request.getParameter("p1");
//            if (p1 != null && !p1.isEmpty()) {
//                client = p1;
//            }
//        } else {
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            String p1 = request.getParameter("p1");
//            if (p1 != null && !p1.isEmpty()) {
//                if (!p1.equals(client)) {
//                    client = p1;
//                }
//            }
//        }
//        return client;
//    }
//
//    public void setClient(String client) {
//        this.client = client;
//    }
//
//    public Boolean getMasterClient() {
//        if (Sessions.exists("sessionClient")) {
//            if (Client.get().getId().equals(new Long(1))) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static Boolean getMasterClientStatic() {
//        if (Sessions.exists("sessionClient")) {
//            if (Client.get().getId().equals(new Long(1))) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Boolean getMasterClientAdministrator() {
//        if (Sessions.exists("sessionClient")) {
//            if (Client.get().getId().equals(new Long(1))) {
//                if (Users.get().getId().equals(new Long(1))) {
//                    return true;
//                }
//                if (Users.get().getLevelUser().getId().equals(new Long(6))) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static String convertToPageStatic(String currentPage) {
//        currentPage = currentPage.substring(currentPage.lastIndexOf("/") + 1, currentPage.lastIndexOf("."));
//        return currentPage;
//    }
//
//    public Routines getRoutine() {
//        return Routines.get();
//    }
//
//    public Boolean disableInsert(Long routine_id) {
//        return !insert(routine_id);
//    }
//
//    public Boolean disableAlter(Long routine_id) {
//        return !alter(routine_id);
//    }
//
//    public Boolean disableDelete(Long routine_id) {
//        return !delete(routine_id);
//    }
//
//    public Boolean disablePrint(Long routine_id) {
//        return !print(routine_id);
//    }
//
//    public Boolean disableGrant(Long routine_id) {
//        return !grant(routine_id);
//    }
//
//    public Boolean insert(Long routine_id) {
//        if (user.getId().equals(new Long(1))) {
//            return true;
//        }
//        if (user.getLevelUser().getNumberLevel() == 6) {
//            return true;
//        }
//        SecurityGroupRoutines sgr2 = findSGR(routine_id);
//        if (sgr2 != null) {
//            if (sgr2.isA()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Boolean alter(Long routine_id) {
//        if (user.getId().equals(new Long(1))) {
//            return true;
//        }
//        if (user.getLevelUser().getNumberLevel() == 6) {
//            return true;
//        }
//        SecurityGroupRoutines sgr2 = findSGR(routine_id);
//        if (sgr2 != null) {
//            if (sgr2.isW()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Boolean delete(Long routine_id) {
//        if (user.getId().equals(new Long(1))) {
//            return true;
//        }
//        if (user.getLevelUser().getNumberLevel() == 6) {
//            return true;
//        }
//        SecurityGroupRoutines sgr2 = findSGR(routine_id);
//        if (sgr2 != null) {
//            if (sgr2.isD()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Boolean grant(Long routine_id) {
//        if (user.getId().equals(new Long(1))) {
//            return true;
//        }
//        if (user.getLevelUser().getNumberLevel() == 6) {
//            return true;
//        }
//        SecurityGroupRoutines sgr2 = findSGR(routine_id);
//        if (sgr2 != null) {
//            if (sgr2.isG()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public Boolean print(Long routine_id) {
//        if (user.getId().equals(new Long(1))) {
//            return true;
//        }
//        if (user.getLevelUser().getNumberLevel() == 6) {
//            return true;
//        }
//        SecurityGroupRoutines sgr2 = findSGR(routine_id);
//        if (sgr2 != null) {
//            if (sgr2.isP()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    protected SecurityGroupRoutines findSGR(Long routine_id) {
//        List<SecurityGroupUsers> sgu = new SecurityGroupUsers().getListSecurityGroupUsers();
//        if (sgu.isEmpty()) {
//            return null;
//        }
//        if(sgr == null || !sgr.getRoutine().getId().equals(routine_id)) {
//            for (int i = 0; i < sgu.size(); i++) {
//                sgr = new SecurityGroupRoutinesDao().find(Client.get().getId(), sgu.get(i).getSecurityGroup().getId(), routine_id);
//                if (sgr != null) {
//                    return sgr;
//                }
//
//            }
//        } else {
//            return sgr;
//        }
//        return null;
//    }
//
//    /**
//     * Executado quando o Servidor de Autorização fizer o redirect.
//     *
//     * @param currentPage
//     * @param code
//     * @return
//     * @throws MalformedURLException
//     * @throws IOException
//     * @throws org.primefaces.json.JSONException
//     */
////    @RequestMapping("/loginfbresponse")
////    public String loginFacebook(String code) throws MalformedURLException, IOException, JSONException {
////
////        loginFacebook.obterUsuarioFacebook(code);
////
////        return "redirect:/";
////    }
//}
