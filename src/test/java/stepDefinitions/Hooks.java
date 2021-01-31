package stepDefinitions;

import cucumber.runtime.StepDefinition;
import org.junit.Before;

public class Hooks {

    //@Before("@DeletePlace")
    @Before
    public void beforeScenario() throws Throwable{
        stepDefinitions sd = new stepDefinitions();
        if(sd.place_Id ==null) {
            sd.addPlacePayloadIsAvailableWith("PY","Filipino","Aussie");
            sd.userCallsWithHttpRequest("AddPlaceAPI", "POST");
            sd.place_idIsCapturesMappedToToBeUsedIn("PY","AddPlaceAPI","POST");

        }


    }
}
