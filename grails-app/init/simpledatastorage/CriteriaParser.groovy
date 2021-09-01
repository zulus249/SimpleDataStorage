package simpledatastorage

import simpledatastorage.utils.Result

import java.text.SimpleDateFormat

/**
 *
 * @todo Consider parsing other formats
 * */
class CriteriaParser {
    static SimpleDateFormat sdfMMDDYY = new SimpleDateFormat("MM/dd/yy")

    static Date[] parsePeriod(String sStartDate, String sEndDate){

        Date startDate
        Date endDate
        try{
            startDate = sdfMMDDYY.parse(sStartDate)
            endDate = sdfMMDDYY.parse(sEndDate)

            return [startDate, endDate]
        }catch(Exception e){
            return null
        }
    }

    static DataHolder parseCriteria(String campaignName, String dataSourceName){

        if(campaignName != null && dataSourceName != null){
            String campaignDataSourceName = "${campaignName}${DataStorage.DSCN_SEPARATOR}${dataSourceName}"
            return Application.dataStorage.campaignDataSources.get(campaignDataSourceName)
        }else if(campaignName != null){
            return Application.dataStorage.campaigns.get(campaignName)

        }else if(dataSourceName != null){
            return Application.dataStorage.dataSources.get(dataSourceName)
        }else{
            return null
        }
    }
}
