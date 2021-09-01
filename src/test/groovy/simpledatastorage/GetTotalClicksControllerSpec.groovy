package simpledatastorage

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class GetTotalClicksControllerSpec extends Specification implements ControllerUnitTest<GetTotalClicksController> {

    DataStorage dataStorage

    def setup() {
        dataStorage = new DataStorage()
        dataStorage.fullImport()
    }

    def cleanup() {
    }

    void "test number of clicks"() {
        expect:"number of clicks"
        
	    dataStorage.dataSources['Facebook Ads'].getClicks(new Date(), new Date()) == 0L
    	    dataStorage.dataSources['Facebook Ads'].getClicks(dataStorage.SDF.parse('01/01/20'), dataStorage.SDF.parse('01/01/20')) == 5L
                //&& dataStorage.dataSources['Facebook Ads'].getClicks(dataStorage.SDF.parse('10/30/19'), dataStorage.SDF.parse('10/30/19')) == 680L
                //&& dataStorage.dataSources['Facebook Ads'].getClicks(dataStorage.SDF.parse('12/01/19'), dataStorage.SDF.parse('12/31/19')) == 12374L
               // && dataStorage.dataSources['Facebook Ads'].getClicks(dataStorage.SDF.parse('10/30/19'), dataStorage.SDF.parse('10/30/19')) == 12374L
                  //  && Application.getDataStorage().dataSources['Facebook Ads'].getClicks('', '') ==
                    //&& Application.getDataStorage().dataSources['Facebook Ads'].getClicks('', '') ==
	
    }
}
