package in.net.dpl.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import in.net.dpl.model.WaterModel;

@Path("/water")
public class api {


  
  
  @POST
  @Path("/push")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response gethrmessage(InputStream incomingData) {
      StringBuilder crunchifyBuilder = new StringBuilder();
      try {
          BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
          String line = null;
          while ((line = in.readLine()) != null) {
              crunchifyBuilder.append(line);
          }
          
          JSONObject jsonObj = new JSONObject(crunchifyBuilder.toString().trim());
          Iterator<String> keys = jsonObj.keys();

          while(keys.hasNext()) {
              String key = keys.next();
              System.out.println("Key-"+key+"Value - "+jsonObj.get(key));  
              if (jsonObj.get(key) instanceof JSONObject) {
            	     
              }
          }
      } catch (Exception e) {
          System.out.println("Error Parsing: - ");
      }
      System.out.println("Data Received: " + crunchifyBuilder.toString());

      // return HTTP response 200 in case of success
      return Response.status(200).entity(crunchifyBuilder.toString()).build();
  }
}