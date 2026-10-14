package tn.esprit.autoloc;

public class Utils {

    private Utils() {
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}