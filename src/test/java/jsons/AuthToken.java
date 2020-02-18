package jsons;

import com.google.gson.JsonObject;
import setup.Config;

public class AuthToken extends Config {

    /**
     * creating JsonObject for authentication request
     * @param username
     * @param password
     * @return json object as String
     */
    public static String authToken_json(String username, String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        return jsonObject.toString();
    }
}