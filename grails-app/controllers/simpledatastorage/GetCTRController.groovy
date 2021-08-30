package simpledatastorage

import simpledatastorage.utils.Result

class GetCTRController {

    def index() {
        String dataSourceName = params.ds
        String campaignName = params.c
        String sStartDate = params.start
        String sEndDate = params.end
        String resultName = "CTR for ${campaignName != null ? campaignName : '*' } campaign and ${dataSourceName != null ? dataSourceName : '*' } data source from $sStartDate to $sEndDate inclusive"
        Double resultCTR

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
        if (impressions == 0L) {
            render(new Result(resultName, '', Result.ERR_NO_IMPRESSIONS))
            return
        }

        resultCTR =  (double)(dh.getClicks(dates[0], dates[1]))/(double)impressions
        render (new Result(resultName, "$resultCTR", Result.OK))
    }
}
