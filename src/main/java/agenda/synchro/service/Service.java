package agenda.synchro.service;

import agenda.synchro.rdv.Database;
import agenda.synchro.rdv.RDV;
import com.owlike.genson.Genson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/rdv")
public class Service {
    @GET
    @Path("/get/{idRDV}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRDVbyId( @PathParam( "idRDV" ) int idRDV ) {
        return Database.list[idRDV].toString();
    }

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRDV() {
        JSONArray jsonArray = new JSONArray();
        for (RDV rdv : Database.list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idRDV", rdv.getIdRDV());
            jsonObject.put("name", rdv.getName());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean updateRDV(RDV rdv){
        System.out.println( "Receive update for " + rdv);
        Database.list[rdv.getIdRDV()] = rdv;
        return true;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean addRDV(RDV rdv){
        Database.list[rdv.getIdRDV()] = rdv;
        return true;
    }
}
