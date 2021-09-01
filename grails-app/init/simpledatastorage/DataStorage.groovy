package simpledatastorage

import java.text.SimpleDateFormat

/**
 * @todo Consider using Redis and cache
 * */
class DataStorage {

    final static String DSCN_SEPARATOR = '@#$'
    static SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yy")

    Map <String, Campaign> campaigns
    Map <String, Datasource> dataSources
    Map <String, CampaignDatasource> campaignDataSources

    void fullImport(){
        campaigns = [:]
        dataSources = [:]
        campaignDataSources = [:]
        load()
    }

    void incrementalImport(){
        load()
    }

    private void load() {
        URL resource = this.class.classLoader.getResource("PIxSyyrIKFORrCXfMYqZBI.csv")
	    new File(resource.getFile()).eachLine { line ->
                    //println(line)
                    String[] fields = line.split(',')
                    if(fields.length == 5){
                        try {
                            String dataSourceName = fields[0]
                            String campaignName = fields[1]
                            Date date = SDF.parse(fields[2])
                            Long clicks = Long.valueOf(fields[3])
                            Long impressions = Long.valueOf(fields[4])

                            if(campaigns[campaignName] == null)
                                    campaigns[campaignName] = new Campaign()
                            Campaign campaign = campaigns[campaignName]

                            if(dataSources[dataSourceName] == null)
                                    dataSources[dataSourceName] = new Datasource()
                            Datasource dataSource = dataSources[dataSourceName]

                            String campaignDataSourceName = "${campaignName}${DSCN_SEPARATOR}${dataSourceName}"
                            if(campaignDataSources[campaignDataSourceName] == null)
                                    campaignDataSources[campaignDataSourceName] = new CampaignDatasource(campaign, dataSource)
                            campaignDataSources[campaignDataSourceName].addRecord(date, clicks, impressions)

                        }catch (Exception e){
                            if(!line.startsWith('Datasource')) //skip header
				    println("Error parsing line \n'$line' :  ${e.getLocalizedMessage()}")
                        }
                    }

            }


    }

}
