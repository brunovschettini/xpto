package br.com.xptosystems.security.access;

//package br.com.placarinterativo.seguranca.acesso;
//
//
//import br.com.placarinterativo.dao.DAO;
//import br.com.placarinterativo.seguranca.Configuracao;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//
//@ManagedBean
//@SessionScoped
//public class ConfiguracaoMB {
//
//    private Configuracao configuracao = new Configuracao();
//
//    public String init() {
//        if (this.configuracao.getId() == -1) {
//            DAO interageDAO = new DAO();
//            this.configuracao = (Configuracao) interageDAO.findObjectByID(1, "Configuracao");
//        }
//        return null;
//    }
//
//    public Configuracao getConfiguracao() {
//        return configuracao;
//    }
//
//    public void setConfiguracao(Configuracao configuracao) {
//        this.configuracao = configuracao;
//    }
//
//}
