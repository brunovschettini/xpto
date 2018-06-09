package br.com.xptosystems.security.access;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        String currentPage = convertToPage(facesContext.getViewRoot().getViewId());
//        if (Sessions.exists("currentPage")) {
//            if (Sessions.getString("currentPage").equals(currentPage)) {
//                return;
//            } else {
//                Sessions.put("currentPage", currentPage);
//            }
//        } else {
//            Sessions.put("currentPage", currentPage);
//        }
        try {
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            Object currentUser = session.getAttribute("sessionUsers");
            if (currentUser == null) {
                if (!currentPage.equals("login") && !currentPage.equals("index")) {
                    if(currentPage.equals("error_401") || currentPage.equals("error_402") || currentPage.equals("error_403") || currentPage.equals("error_404") || currentPage.equals("error_500")) {
                       return; 
                    }
                    nh.handleNavigation(facesContext, null, "error_401");
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object currentUser = session.getAttribute("sessionUsers");
        if (currentUser == null) {
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
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
//                            Friends f = (Friends) Sessions.getObject("friendsRegister", true);
//                            f.setConfirmCode(null);
//                            f.setUsersFriends(u);
//                            dao.update(f, true);
//                        }
//                        Sessions.put("sessionUsers", u);
//                        Sessions.put("userName", u.getPerson().getName());
//                    }
//                }
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        }
        }
    }

    private String convertToPage(String currentPage) {
        currentPage = currentPage.substring(currentPage.lastIndexOf("/") + 1, currentPage.lastIndexOf("."));
        return currentPage;
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
