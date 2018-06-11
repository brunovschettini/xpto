package br.com.xptosystems.security.ws;

import br.com.xptosystems.security.UserToken;
import br.com.xptosystems.utils.Defaults;
import br.com.xptosystems.utils.Messages;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

public class UsersWS {

    public UserToken auth(String login, String password) {
        Client client = Client.create();
        WebResource webResource
                = client.resource(Defaults.WEBSERVICE)
                        .path("auth/in");
        // String query = "{\"username\":\"" + login + "\",\"password\":\"" + password + 
        String query = "login=" + login + "&password=" + password;
        ClientResponse response
                = webResource.type(MediaType.APPLICATION_JSON)
                        .post(ClientResponse.class, query);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        Gson gson = new Gson();
        String result = response.getEntity(String.class);
        NotifyResponse notifyResponse = null;
        try {
            notifyResponse = gson.fromJson(result, NotifyResponse.class);
            if (notifyResponse != null) {
                Messages.warn("Validação", notifyResponse.getObject().toString(), Boolean.TRUE);
                return null;
            }
        } catch (Exception e) {

        }
        try {
            UserToken ut = gson.fromJson(result, UserToken.class);
            if (ut != null) {
                return ut;
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
