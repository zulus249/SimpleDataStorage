package simpledatastorage

class DataHolder {

    final static int YEAR = 0
    final static int MONTH = 1
    final static int DAY = 2

    protected Map <Date, Long> dailyClicks = [:]
    protected Map <Date, Long> dailyImpressions = [:]
    protected Map <String, Long> monthlyClicks = [:]
    protected Map <String, Long> monthlyImpressions = [:]
    protected Map <Integer, Long> annualClicks = [:]
    protected Map <Integer, Long> annualImpressions = [:]

    void addRecord(Date date, long clicks, long impressions) {

        Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        Integer year = calendar.get(Calendar.YEAR)
        Integer month = calendar.get(Calendar.MONTH) + 1
        String yearMonth = "$year|$month"

        if(dailyClicks[date] == null){
            dailyClicks[date] = clicks
        }else {
            dailyClicks[date] = dailyClicks[date] + clicks
        }
        if(dailyImpressions[date] == null){
            dailyImpressions[date] = impressions
        }else {
            dailyImpressions[date] = dailyImpressions[date] + impressions
        }

        if(monthlyClicks[yearMonth] == null){
            monthlyClicks[yearMonth] = clicks
        }else {
            monthlyClicks[yearMonth] = monthlyClicks[yearMonth] + clicks
        }
        if(monthlyImpressions[yearMonth] == null){
            monthlyImpressions[yearMonth] = impressions
        }else {
            monthlyImpressions[yearMonth] = monthlyImpressions[yearMonth] + impressions
        }

        if(annualClicks[year] == null){
            annualClicks[year] = clicks
        }else {
            annualClicks[year] = annualClicks[year] + clicks
        }
        if(annualImpressions[year] == null){
            annualImpressions[year] = impressions
        }else {
            annualImpressions[year] = annualImpressions[year] + impressions
        }
    }

    private int[] splitDate(Date date){
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        int year = calendar.get(Calendar.YEAR)
        int month = calendar.get(Calendar.MONTH) + 1
        int day = calendar.get(Calendar.DAY_OF_MONTH)

        return [year, month, day]
    }

    /** Method returns total clicks for period given 
     * @param startDate
     * @param endDate
     * @return total number of clicks between startDate and endDate inclusive
     * */
    public long getClicks(Date startDate, Date endDate){
        int[] itStart = splitDate(startDate)
        int[] itEnd = splitDate(endDate)
        long clicks
        long totalClicks = 0

        // get full years
        for(Integer year = itStart[YEAR] + 1; year < itEnd[YEAR]; year++){
            clicks = annualClicks[year]
            if(clicks) 
                    totalClicks += clicks
        }

        // get full months
        if (itStart[YEAR] == itEnd[YEAR]){
            for(int month = itStart[MONTH] + 1; month < itEnd[MONTH]; month++){
                clicks = monthlyClicks["${itStart[YEAR]}|$month"]
                if(clicks)
                        totalClicks += clicks
            }
        } else if (itStart[YEAR] < itEnd[YEAR]){ // no negative time
            for(int month = itStart[MONTH] + 1; month <= Calendar.DECEMBER + 1; month++){
                clicks = monthlyClicks["${itStart[YEAR]}|$month"]
                if(clicks)
                        totalClicks += clicks
            }
            for(int month = Calendar.JANUARY + 1; month < itEnd[MONTH]; month++){
                clicks = monthlyClicks["${itEnd[YEAR]}|$month"]
                if(clicks)
                        totalClicks += clicks
            }
        }

        // get days
        Calendar calendar = Calendar.getInstance()
        if(itStart[YEAR] == itEnd[YEAR] && itStart[MONTH] == itEnd[MONTH]) {
            for(int day = itStart[DAY]; day <= itEnd[DAY]; day++){
                calendar.set(itStart[YEAR], itStart[MONTH] - 1, day)
                clicks = dailyClicks[calendar.getTime()]
                if(clicks)
                        totalClicks += clicks
            }
        } else if(itStart[YEAR] < itEnd[YEAR] || itStart[YEAR] == itEnd[YEAR] && itStart[MONTH] < itEnd[MONTH]){
            for(int day = itStart[DAY]; day <= 31; day++){
                calendar.set(itStart[YEAR], itStart[MONTH] - 1, day)
                clicks = dailyClicks[calendar.getTime()]
                if(clicks)
                        totalClicks += clicks
            }
            for(int day = 1; day <= itEnd[DAY]; day++){
                calendar.set(itEnd[YEAR], itEnd[MONTH] - 1, day)
                clicks = dailyClicks[calendar.getTime()]
                if(clicks)
                        totalClicks += clicks
            }
        }
        
        return totalClicks
    }

    long getImpressions(Date startDate, Date endDate){
        int[] itStart = splitDate(startDate)
        int[] itEnd = splitDate(endDate)
        long impressions
        long totalImpressions = 0

        // get full years
        for(Integer year = itStart[YEAR] + 1; year < itEnd[YEAR]; year++){
            impressions = annualImpressions[year]
            if(impressions)
                totalImpressions += impressions
        }

        // get full months
        if (itStart[YEAR] == itEnd[YEAR]){
            for(int month = itStart[MONTH] + 1; month < itEnd[MONTH]; month++){
                impressions = monthlyImpressions["${itStart[YEAR]}|$month"]
                if(impressions)
                    totalImpressions += impressions
            }
        } else if (itStart[YEAR] < itEnd[YEAR]){ // no negative time
            for(int month = itStart[MONTH] + 1; month <= Calendar.DECEMBER + 1; month++){
                impressions = monthlyImpressions["${itStart[YEAR]}|$month"]
                if(impressions)
                    totalImpressions += impressions
            }
            for(int month = Calendar.JANUARY + 1; month < itEnd[MONTH]; month++){
                impressions = monthlyImpressions["${itEnd[YEAR]}|$month"]
                if(impressions)
                    totalImpressions += impressions
            }
        }

        // get days
        Calendar calendar = Calendar.getInstance()
        if(itStart[YEAR] == itEnd[YEAR] && itStart[MONTH] == itEnd[MONTH]) {
            for(int day = itStart[DAY]; day <= itEnd[DAY]; day++){
                calendar.set(itStart[YEAR], itStart[MONTH] - 1, day)
                impressions = dailyImpressions[calendar.getTime()]
                if(impressions)
                    totalImpressions += impressions
            }
        } else if(itStart[YEAR] < itEnd[YEAR] || itStart[YEAR] == itEnd[YEAR] && itStart[MONTH] < itEnd[MONTH]){
            for(int day = itStart[DAY]; day <= 31; day++){
                calendar.set(itStart[YEAR], itStart[MONTH] - 1, day)
                impressions = dailyImpressions[calendar.getTime()]
                if(impressions)
                    totalImpressions += impressions
            }
            for(int day = 1; day <= itEnd[DAY]; day++){
                calendar.set(itEnd[YEAR], itEnd[MONTH] - 1, day)
                impressions = dailyImpressions[calendar.getTime()]
                if(impressions)
                    totalImpressions += impressions
            }
        }

        return totalImpressions
    }

}
