package resources;

public enum resourceAPI {
// Constants that are used within the framework
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    UpdatePlaceAPI("/maps/api/place/update/json");

    private String resource;

    // Constructor
    resourceAPI(String resource) {
        this.resource=resource;
    }

    public String getResource() {
        return resource;
    }
}
