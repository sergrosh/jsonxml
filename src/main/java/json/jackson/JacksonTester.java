package json.jackson;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sroshchupkin on 18/11/15.
 */
public class JacksonTester {
    public static void main(String args[]){
        JacksonTester tester = new JacksonTester();

        try {
            Student student = new Student();
            student.setAge(28);
            student.setName("Alexander");
            tester.writeJSON(student);

            Student student1 = tester.readJSON();
            System.out.println(student1);

            ObjectMapper mapper = new ObjectMapper();

            Map<String,Object> studentDataMap = new HashMap<String,Object>();
            int[] marks = {1,2,3};

            Student student2 = new Student();

            student2.setAge(25);
            student2.setName("Anatoliy");

            // JAVA Object
            studentDataMap.put("student", student2);

            // JAVA String
            studentDataMap.put("name", "Leo");

            // JAVA Boolean
            studentDataMap.put("verified", Boolean.FALSE);

            // Array
            studentDataMap.put("marks", marks);


            /**
             * -----------------------------------------------------------------
             */

            mapper.writeValue(new File("studentList.json"), studentDataMap);

            studentDataMap = mapper.readValue(new File("studentList.json"), Map.class);

            System.out.println(studentDataMap.get("student"));
            System.out.println(studentDataMap.get("name"));
            System.out.println(studentDataMap.get("verified"));
            System.out.println(studentDataMap.get("marks"));



            ObjectMapper mapper1 = new ObjectMapper();
            String jsonString = "{\"name\":\"Steven\",  \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
            JsonNode  rootNode = mapper1.readTree(jsonString);

            JsonNode nameNode = rootNode.path("name");
            System.out.println("Name: "+ nameNode.getTextValue());

            JsonNode ageNode = rootNode.path("age");
            System.out.println("Age: " + ageNode.getIntValue());

            JsonNode verifiedNode = rootNode.path("verified");
            System.out.println("Verified: " + (verifiedNode.getBooleanValue() ? "Yes":"No"));

            JsonNode marksNode = rootNode.path("marks");
            Iterator<JsonNode> iterator = marksNode.getElements();
            System.out.print("Marks: [ ");

            while (iterator.hasNext()) {
                JsonNode marks2 = iterator.next();
                System.out.print(marks2.getIntValue() + " ");
            }

            System.out.println("]");
        }
        catch (JsonParseException e) { e.printStackTrace(); }
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void writeJSON(Student student) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), student);
    }

    private Student readJSON() throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(new File("student.json"), Student.class);
        return student;
    }
}
