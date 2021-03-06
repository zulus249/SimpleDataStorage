package simpledatastorage

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import groovy.transform.CompileStatic

@CompileStatic
class Application extends GrailsAutoConfiguration {
    static DataStorage dataStorage

    static void main(String[] args) {
        GrailsApp.run(Application, args)
        dataStorage = new DataStorage('PIxSyyrIKFORrCXfMYqZBI.csv')
        dataStorage.fullImport()
    }
}
