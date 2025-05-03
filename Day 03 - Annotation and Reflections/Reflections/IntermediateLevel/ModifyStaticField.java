package IntermediateLevel;

import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "DEFAULT_KEY";

    public static String getApiKey() {
        return API_KEY;
    }
}

public class ModifyStaticField {
    public static void main(String[] args) throws Exception {
        Class<Configuration> configClass = Configuration.class;

        Field apiKeyField = configClass.getDeclaredField("API_KEY");

        apiKeyField.setAccessible(true);

        apiKeyField.set(null, "UPDATED_SECRET_KEY");

        System.out.println("Updated API Key: " + Configuration.getApiKey());
    }
}

