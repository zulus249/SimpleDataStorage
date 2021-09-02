<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Simple Data storage</title>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li class="dropdown-item"><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
            <li class="dropdown-item"><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li class="dropdown-item"><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li class="dropdown-item"><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li class="dropdown-item"><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Siple Data Storage</h1>
        <div id="examples" role="navigation">
           <h2>API Syntax:</h2><br/>
            get{Clicks|Impressions|CTR}?start=[MM/dd/yy]&amp;end=[MM/dd/yy]&amp;{c=[CAMPAIGN]|ds=[DATA_SOURCE]}<br/>
            <h2>API Usage Examples:</h2>
            <li>
                <ul>
                    <li><a href="http://localhost:8080/getClicks?c=Adventmarkt Touristik&ds=Google Ads&start=01/01/18&end=01/01/20">http://localhost:8080/getClicks?c=Adventmarkt Touristik&ds=Google Ads&start=01/01/18&end=01/01/20</a></li>
                    <li><a href="http://localhost:8080/getImpressions?&ds=Facebook Ads&start=01/07/15&end=01/01/20">http://localhost:8080/getImpressions?&ds=Facebook Ads&start=01/07/15&end=01/01/20</a></li>
                    <li><a href="http://localhost:8080/getCTR?c=Adventmarkt Touristik&start=01/07/18&end=01/01/19">http://localhost:8080/getCTR?c=Adventmarkt Touristik&start=01/07/18&end=01/01/19</a></li>
                </ul>
            </li>
        </div>
    </section>
</div>

</body>
</html>
