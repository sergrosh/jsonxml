package json.jackson;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;

/**
 * Created by sroshchupkin on 18/11/15.
 */
public class JacksonSimpleTester {
    public static void main(String args[]){

        ObjectMapper  mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Alexander\", \"age\":28}";

        try{
            //JSON to Object Conversion
            Student student = mapper.readValue(jsonString, Student.class);

            System.out.println(student);

            mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);

            //Object to JSON Conversion
            jsonString = mapper.writeValueAsString(student);

            System.out.println(jsonString);
        }
        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }
}

