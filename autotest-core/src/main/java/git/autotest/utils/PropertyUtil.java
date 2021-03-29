package git.autotest.utils;

import java.io.IOException;
import java.util.Properties;

public enum PropertyUtil {
    INSTANCE;
    static Properties properties = new Properties();

    static {
        try {
            properties.load(
                    PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyUtil.get("TESTCASE_NUM"));
    }
}
