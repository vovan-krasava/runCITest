import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {


        JsonNode jsonNode, jsonNode2, jsonNode3;
        try {
            //System.out.println("!"+getFileFromResource("data.json"));
            String json = readResources("data.json");
            jsonNode = new ObjectMapper().readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //ReadFromJsonFile.collectRepeatableFields(jsonNode);
        ///ReadFromJsonFile.collectRepeatableValues(jsonNode);
        ReadFromJsonFile.traverseJsonTree(jsonNode);
        System.out.println("cars count: "+ ReadFromJsonFile.FIELD_COUNTER);
        System.out.println("cars list: "+ ReadFromJsonFile.values);

        //
//List<JsonNode> datasets = StreamSupport
//        .stream(ReadFromJsonFile.values.get(1).spliterator(), false)
//        .collect(Collectors.toList());
//Collections.unmodifiableList(result);

        List<Object> res = ReadFromJsonFile.values.stream().map(it ->
                {
                    if(it.isArray()) {
                        return StreamSupport
                                .stream(it.spliterator(), false).map(JsonNode::asText);
                    }
                    return it.asText();
                }
        ).collect(Collectors.toList());
        int frequency =  Collections.frequency(res, "bmw");
        System.out.println("frequency bmw: "+frequency);
    }

    public static String readResources(String fileName) throws IOException {
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fileName);
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
