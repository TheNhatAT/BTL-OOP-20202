package thenhat.code.managerwebapp.model.email;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbstractEmailContext {
    //== fields ==
    private String from;
    private String to;
    private String email;
    private String subject;
    private String attachment;
    private String fromDisplay;
    private String emailLanguage;
    private String templateLocation;
    private Map<String, Object> context;

    //== constructor ==
    public AbstractEmailContext() {
        this.context = new HashMap<>();
    }

    //== init method to init the context of email ==
    public <T> void init(T context) {
        //== do any configuration in here ==
    }

    //== to put the context ==
    public Object put(String key, Object value) {
        return key == null ? null : this.context.put(key.intern(), value);
    }
}
