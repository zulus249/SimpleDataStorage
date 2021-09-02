package simpledatastorage

class CampaignDatasource extends DataHolder{
    private Campaign campaign
    private Datasource datasource

    CampaignDatasource(Campaign campaign, Datasource dataSource){
        this.campaign = campaign
        this.datasource = dataSource
    }

}
