package org.selenium.pom.constants;

import java.util.Map;

public class Codes {

    public static String getCountryCode(String countryName) {
        Map<String, String> countryMap = Map.of(
                "India", "IN",
                "United States (US)", "US");
        return countryMap.get(countryName);
    }

    public static String getStateCode(String stateName) {
        Map<String, String> stateMap = Map.of(
                "Maharashtra", "MH",
                "California", "CA",
                "Texas", "TX");
        return stateMap.get(stateName);
    }
}
