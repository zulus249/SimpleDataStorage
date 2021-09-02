package simpledatastorage

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import java.lang.Math;

class GetCTRControllerSpec extends Specification implements ControllerUnitTest<GetCTRController> {

    DataStorage dataStorage
    static double ERR = 0.00001

    def setup() {
        dataStorage = new DataStorage('PIxSyyrIKFORrCXfMYqZBI.csv')
        dataStorage.fullImport()
    }

    def cleanup() {
    }

    void "Test CTR"() {
        expect:"For sure it works :-)"
        dataStorage.dataSources['Facebook Ads'].getCTR(new Date(), new Date()) == null
        Math.abs(dataStorage.dataSources['Facebook Ads'].getCTR(dataStorage.SDF.parse('01/01/20'), dataStorage.SDF.parse('01/01/20')) - 0.007396449704142) <= ERR
        Math.abs(dataStorage.dataSources['Facebook Ads'].getCTR(dataStorage.SDF.parse('11/30/19'), dataStorage.SDF.parse('11/30/19')) - 0.018028356717136) <= ERR
    	Math.abs(dataStorage.dataSources['Facebook Ads'].getCTR(dataStorage.SDF.parse('12/01/19'), dataStorage.SDF.parse('12/31/19')) - 0.016798873739643) <= ERR
        Math.abs(dataStorage.dataSources['Facebook Ads'].getCTR(dataStorage.SDF.parse('01/01/10'), dataStorage.SDF.parse('01/01/30')) - 0.028262185750307) <= ERR
        Math.abs(dataStorage.campaignDataSources["Versicherungen${dataStorage.DSCN_SEPARATOR}Facebook Ads"].getCTR(dataStorage.SDF.parse('12/01/19'), dataStorage.SDF.parse('12/31/19')) - 0.152542372881356) <= ERR
    }
}
