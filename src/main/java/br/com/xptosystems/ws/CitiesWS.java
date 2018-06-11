package br.com.xptosystems.ws;

import br.com.xptosystems.address.Cities;
import br.com.xptosystems.address.CitiesComparator;
import br.com.xptosystems.address.dao.CitiesCsv;
import br.com.xptosystems.utils.NotifyResponse;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/cities")
public class CitiesWS {

    @PUT
    @Path("/store")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response store(@FormParam("cityjson") String cityjson) {
        NotifyResponse notifyResponse = new NotifyResponse();
        Gson gson = new Gson();
        if (cityjson == null || !cityjson.isEmpty()) {
            notifyResponse.setObject("Nenhum registro encontrado para ser gravado!");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        Cities city = gson.fromJson(cityjson, Cities.class);
        if (city.getIbgeId() == null) {
            notifyResponse.setObject("Informar o Id do Ibge!");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        if (city.getName() == null || city.getName().isEmpty()) {
            notifyResponse.setObject("Informar o nome da ciade!");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        if (city.getUf() == null || city.getUf().isEmpty()) {
            notifyResponse.setObject("Informar a UF / Estado da ciade!");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        if (city.getLat() == null || city.getLat() == 0) {
            notifyResponse.setObject("Informar as cordenadas de latitude!");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        if (city.getLon() == null || city.getLon() == 0) {
            notifyResponse.setObject("Informar as cordenadas de longitude!");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        if (city.getCapital() == null) {
            notifyResponse.setObject("Informar se a cidade é uma capital!");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        return Response.status(200).entity(gson.toJson(notifyResponse)).build();
    }

    @DELETE
    @Path("/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response delete(@FormParam("ibge_id") String ibge_id) {
        NotifyResponse notifyResponse = new NotifyResponse();
        Gson gson = new Gson();
        if (ibge_id == null || !ibge_id.isEmpty()) {
            notifyResponse.setObject("Empty / null ibge id");
            return Response.status(200).entity(gson.toJson(notifyResponse)).build();
        }
        notifyResponse.setObject("Sucess");
        return Response.status(200).entity(gson.toJson(notifyResponse)).build();
    }

    @GET
    @Path("/capitals")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response capitals() {
        List<Cities> list = new CitiesCsv().capitals();
        Collections.sort(list);
        return Response.status(200).entity(new Gson().toJson(list)).build();
    }

    @GET
    @Path("/min_max_cities_by_state")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response min_max_cities_by_state() {
        List<CitiesCsv.Ufs> list = new CitiesCsv().min_max_cities_by_state();
        return Response.status(200).entity(new Gson().toJson(list)).build();
    }

    @GET
    @Path("/count_by_state/{uf}")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response count_cities_by_state(@PathParam("uf") String uf) {
        Integer count = new CitiesCsv().count_cities_by_state(uf);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uf", uf);
        jSONObject.put("amount", count);
        return Response.status(200).entity(jSONObject.toString()).build();
    }

    @GET
    @Path("/find/ibge_id/{ibge_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response find_by_ibge_id(@PathParam("ibge_id") String ibge_id) {
        Cities cities = new CitiesCsv().find_by_ibge_id(ibge_id);
        return Response.status(200).entity(new Gson().toJson(cities)).build();
    }

    @GET
    @Path("/find/uf/{uf}")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response find_by_uf(@PathParam("uf") String uf) {
        List<Cities> list = new CitiesCsv().find_by_uf(uf);
        Collections.sort(list);
        return Response.status(200).entity(new Gson().toJson(list)).build();
    }

    @GET
    @Path("/total")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response total() {
        Integer total = new CitiesCsv().total();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("total", total);
        return Response.status(200).entity(jSONObject.toString()).build();
    }

    @GET
    @Path("/total/{column}")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response total(@PathParam("column") String column) {
        Integer total = new CitiesCsv().total(column);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("total", total);
        return Response.status(200).entity(jSONObject.toString()).build();
    }

    @GET
    @Path("/find/{column}")
    @Produces({MediaType.APPLICATION_JSON})
    public synchronized Response find(@PathParam("column") String column, @PathParam("query") String query) {
        List<Cities> list = new CitiesCsv().find(column, query);
        Integer total = new CitiesCsv().total(column);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("total", total);
        return Response.status(200).entity(jSONObject.toString()).build();
    }

}
