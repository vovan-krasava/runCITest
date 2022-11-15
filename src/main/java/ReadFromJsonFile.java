import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadFromJsonFile {
    public static int FIELD_COUNTER=0;
    public static int VALUE_COUNTER=0;

    public static List<JsonNode> values = new ArrayList<>();
    public static void traverseJsonTree(JsonNode node) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey();
                JsonNode fieldValue = field.getValue();
                if (fieldName.equalsIgnoreCase("cars")) {
                    values.add(fieldValue);
                    continue;
                }
                traverseJsonTree(fieldValue);
            }
        } else if (node.isArray()) {
            for (JsonNode cNode : node) {
                traverseJsonTree(cNode);
            }
        } else {
            System.out.println("!!!!" + node);

        }
    }

//                if (tmp.getKey().equalsIgnoreCase("cars")) {
//                    System.out.println("fields: "+tmp);
//                    FIELD_COUNTER += 1;
//                    values.add(tmp.getValue().asText());
////                    if (tmp.getValue().isArray())
////                        values.addAll( new ObjectMapper().convertValue(tmp.getValue(), new TypeReference<List<String>>(){}) );
////                    else if(tmp.getValue().isValueNode()) {
////                        values.add(tmp.getValue().asText());
////                    }
//                }
//

    public static void collectRepeatableValues(JsonNode node) {
        node.forEach(
                it -> {
                    if (it.fields().hasNext()) {
                        collectRepeatableValues(it);
                    } else {
                        if(it.elements().next().asText().equals("bmw")) {
                            FIELD_COUNTER +=1;
                            System.out.println("bmw count: "+FIELD_COUNTER);
                        }
                    }
                }
        );
    }


        public static void readFromJsonFile(String path) {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {

            jsonArray = (JSONArray) parser.parse(new FileReader(path));

//            JSONObject data = (JSONObject) parser.parse(new FileReader(path));
//            String json = data.toJSONString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (Object o : jsonArray) {
            JSONObject person = (JSONObject) o;

            String strName = (String) person.get("name");
            System.out.println("Name::::" + strName);

            String strCity = (String) person.get("city");
            System.out.println("City::::" + strCity);

            JSONArray arrays = (JSONArray) person.get("cars");
            for (Object object : arrays) {
                System.out.println("cars::::" + object);
            }
            String strJob = (String) person.get("job");
            System.out.println("Job::::" + strJob);
            System.out.println();

        }

    }

}
