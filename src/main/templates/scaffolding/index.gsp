<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="\${message(code: '${propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-${propertyName}" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                %{--<li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <h2>modified</h2>
        <div id="list-${propertyName}" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="\${flash.message}">
                <div class="message" role="status">\${flash.message}</div>
            </g:if>

            <%  //http://techqa.info/programming/question/36156550/grails-scaffolding-templates---get-properties-from-domain-class
            def grailsApplication = grails.util.Holders.grailsApplication
            domainObject = grailsApplication.domainClasses.find {it.clazz.simpleName.toLowerCase() == propertyName}.clazz.newInstance()
            domainClass = grailsApplication.getDomainClass(domainObject.class.name)
            props = domainClass.persistentProperties*.name
            System.out.println("Dyn Props: " + props)
            %>

            <f:table collection="\${${propertyName}List}"   properties="${props}"/>
            %{--<f:table collection="\${${propertyName}List}" />--}%

            <div class="pagination">
                <g:paginate total="\${${propertyName}Count ?: 0}" />
            </div>
        </div>
    </body>
</html>