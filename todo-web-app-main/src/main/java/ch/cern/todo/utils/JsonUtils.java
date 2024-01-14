package ch.cern.todo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonUtils {
    private static Log log = LogFactory.getLog(JsonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static String valueToString(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Could not parse value " + value + ", " + e);
            throw new RuntimeException(e);
        }
    }
}
