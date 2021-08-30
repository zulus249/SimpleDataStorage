package simpledatastorage

class CampaignDatasource extends DataHolder{
    private Campaign campaign
    private Datasource datasource

    CampaignDatasource(Campaign campaign, Datasource dataSource){
        this.campaign = campaign
        this.datasource = dataSource
    }

    void addRecord(Date date, long clicks, long impressions) {
        super.addRecord(date, clicks, impressions)
        campaign.addRecord(date, clicks, impressions)
        datasource.addRecord(date, clicks, impressions)
    }
}
