package json.jackson.annotations;

import org.codehaus.jackson.map.annotate.JsonView;

import java.util.List;

/**
 * Created by sroshchupkin on 23/11/15.
 */
public class User {

    @JsonView (Views.NameOnly.class)
    private String name;

    @JsonView(Views.AgeAndName.class)
    private int age;

    private List<String> messages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
