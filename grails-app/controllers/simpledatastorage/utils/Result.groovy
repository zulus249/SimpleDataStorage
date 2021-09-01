package simpledatastorage.utils

import simpledatastorage.Application

/** @todo: Consider using JSON parser
 * */
class Result {

    final static int OK = 0
    final static int ERR_PARSE_DATE = 1
    final static int ERR_NO_DATA_OR_BAD_CRITERIA = 2
    final static int ERR_NO_IMPRESSIONS = 3

    final static String[] ERROR_MESSAGES = [
            'OK',
            'Parsing dates ERROR',
            'No data for criteria given or invalid criteria ERROR',
            'No impressions for criteria given ERROR',
    ]

    private String resultName
    private String resultValue
    private int errorCode

    Result (String resultName, String resultValue, int errorCode){
        this.resultName = resultName
        this.resultValue = resultValue
        this.errorCode = errorCode
    }

    @Override
    String toString() {
        return "{\"resultName\":\"$resultName\", \"resultValue\":\"$resultValue\", \"errorCode\":$errorCode, \"errorMessage\":\"${ERROR_MESSAGES[errorCode]}\"}"
    }
}
