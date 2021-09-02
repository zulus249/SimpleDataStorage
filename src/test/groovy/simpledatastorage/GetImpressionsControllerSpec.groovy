package simpledatastorage

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class GetImpressionsControllerSpec extends Specification implements ControllerUnitTest<GetImpressionsController> {

    DataStorage dataStorage

    def setup() {
        dataStorage = new DataStorage('PIxSyyrIKFORrCXfMYqZBI.csv')
        dataStorage.fullImport()
    }

    def cleanup() {
    }

    void "test number of impressions"() {
        expect:"number of impressions"
        
        dataStorage.dataSources['Facebook Ads'].getImpressions(new Date(), new Date()) == 0L
        dataStorage.dataSources['Facebook Ads'].getImpressions(dataStorage.SDF.parse('01/01/20'), dataStorage.SDF.parse('01/01/20')) == 676L
        dataStorage.dataSources['Facebook Ads'].getImpressions(dataStorage.SDF.parse('11/30/19'), dataStorage.SDF.parse('11/30/19')) == 27013L
    	dataStorage.dataSources['Facebook Ads'].getImpressions(dataStorage.SDF.parse('12/01/19'), dataStorage.SDF.parse('12/31/19')) == 736597L
        dataStorage.dataSources['Facebook Ads'].getImpressions(dataStorage.SDF.parse('11/30/19'), dataStorage.SDF.parse('12/31/19')) == 736597L + 27013L
        dataStorage.dataSources['Facebook Ads'].getImpressions(dataStorage.SDF.parse('12/01/19'), dataStorage.SDF.parse('01/01/20')) == 736597L +  676L
        dataStorage.dataSources['Facebook Ads'].getImpressions(dataStorage.SDF.parse('11/30/19'), dataStorage.SDF.parse('01/01/20')) == 736597L + 27013L + 676L
        dataStorage.dataSources['Facebook Ads'].getImpressions(dataStorage.SDF.parse('01/01/10'), dataStorage.SDF.parse('01/01/30')) == 6106251L
        dataStorage.campaignDataSources["Versicherungen${dataStorage.DSCN_SEPARATOR}Facebook Ads"].getImpressions(dataStorage.SDF.parse('12/01/19'), dataStorage.SDF.parse('12/31/19')) == 236L

    }
}
