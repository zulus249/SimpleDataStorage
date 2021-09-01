package simpledatastorage

import simpledatastorage.utils.Result

class GetImpressionsController {

    def index() {
        String dataSourceName = params.ds
        String campaignName = params.c
        String sStartDate = params.start
        String sEndDate = params.end
        String resultName = "Total impressions for ${campaignName != null ? campaignName : '*' } campaign and ${dataSourceName != null ? dataSourceName : '*' } data source from $sStartDate to $sEndDate inclusive"

        DataHolder dh

        Date[] dates = CriteriaParser.parsePeriod(sStartDate, sEndDate)
        if (dates == null){
            render(new Result(resultName, '', Result.ERR_PARSE_DATE))
            return
        }

        dh = CriteriaParser.parseCriteria(campaignName, dataSourceName)
        if( dh == null){
            render(new Result(resultName, '', Result.ERR_NO_DATA_OR_BAD_CRITERIA))
            return
        }

        long impressions = dh.getImpressions(dates[0], dates[1])

        render (new Result(resultName, "$impressions", Result.OK))
    }
}
