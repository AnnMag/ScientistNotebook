package controllers;
import com.datastax.driver.core.schemabuilder.Create;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import models.DatabaseConnector;
import models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




/**
 * Created by annikamagnusson on 20/04/15.
 */
public class UserController {

    public void mappingTest(){
        DatabaseConnector db = new DatabaseConnector();
        db.connectDefault();
        try {
            Mapper<User> mapper = new MappingManager(db.getSession()).mapper(User.class);
            User user = new User("Annika", "Magnusson", "an@mail.com", "password");
            mapper.save(user);
            User whose = mapper.get("annika@mail.com");
            System.out.println("User name: " + whose.getLastName());
            mapper.delete(user);
        } catch (Exception e){
            System.out.println(e);
        }
        db.close();


    }

    public void createUser(String strUser){
        JSONObject jObj = null;
        try{
            jObj = (JSONObject) new JSONParser().parse(strUser);
        } catch (Exception e){
            System.out.println(e);
        }
        try{
            String firstName = (String) jObj.get("firstName");
            String lastName = (String) jObj.get("lastName");
            String email = (String) jObj.get("email");
            String password = (String) jObj.get("password");

            User user = new User(firstName, lastName, email, password);
            System.out.printf("First Name: %s\nLast Name: %s",user.getFirstName(), user.getLastName());
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public JSONObject createUserJson(String firstName, String lastName, String email){
        JSONObject userJson = new JSONObject();
        userJson.put("firstName", firstName);
        userJson.put("lastName", lastName);
        userJson.put("email", email);

        //Only needs to be implementet when we need to get multiple users
        //JSONArray ja = new JSONArray();
        //ja.add(jObj);
        //JSONObject mainObj = new JSONObject();
        //mainObj.put("users", ja);
        //System.out.println(mainObj);
        return userJson;
    }

    public JSONObject getUser(String userId){
        //Contact DB to get user

        String firstName = "Annika";
        String lastName = "Magnusson";
        String email = "email@email.com";

        JSONObject user = createUserJson(firstName, lastName, email);
        return user;
    }
}
