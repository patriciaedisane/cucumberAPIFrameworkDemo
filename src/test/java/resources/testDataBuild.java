package resources;

import pojo.addPlaceAPI;
import pojo.location;

import java.util.ArrayList;
import java.util.List;
// Test Data generated via Getters and Setters

public class testDataBuild {
    public addPlaceAPI addPlacePayLoad(String name, String language, String address) {
        addPlaceAPI p = new addPlaceAPI();
        p.setAccuracy(100);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("04232323");
        p.setWebsite("www.py.com");
        p.setName(name);

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);

        // Adding values into the fields under location list in the JSON
        location l = new location();
        l.setLng(-34);
        l.setLat(38);

        p.setLocation(l);


        return p;

    }

    public String deletePlacePayLoad (String placeId) {
        String payload = "{\n" +
                "    \"place_id\": \"" + placeId+"\"\n" +
                "}";
        return payload;
    }
}
