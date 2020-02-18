package jsons;

import com.google.gson.JsonObject;
import setup.Config;

public class SubmitReport extends Config {

    /**
     * * creating JsonObject for submit request
     * @param priority
     * @param report
     * @return json as string
     */
    public static String submitReport_json(int priority, String report) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("priority", priority);
        jsonObject.addProperty("report", report);
        return jsonObject.toString();
    }

    /**
     * * creating JsonObject for submit request
     * @param priority
     * @param report
     * @return
     */
    public static String submitReport_json_negative(int priority, int report) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("priority", priority);
        jsonObject.addProperty("report", report);
        return jsonObject.toString();
    }
}
