package json.jackson.annotations;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sroshchupkin on 23/11/15.
 */
public class JacksonExample {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        //By default all fields without explicit view definition are included, disable this
        mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);

        //For testing
        User user = createDummyUser();

        try {
            //display name only
            String jsonInString = mapper.writerWithView(Views.NameOnly.class).writeValueAsString(user);
            System.out.println(jsonInString);

            //display name and age
            jsonInString = mapper.writerWithView(Views.AgeAndName.class).writeValueAsString(user);
            System.out.println(jsonInString);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static User createDummyUser(){

        User user = new User();

        user.setName("John");
        user.setAge(33);

        List<String> msg = new ArrayList<>();
        msg.add("hello jackson 1");
        msg.add("hello jackson 2");
        msg.add("hello jackson 3");

        user.setMessages(msg);

        return user;

    }
}