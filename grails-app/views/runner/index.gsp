<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'runner.label', default: 'Runner')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-runner" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-runner" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            %{--<%  //http://techqa.info/programming/question/36156550/grails-scaffolding-templates---get-properties-from-domain-class--}%
                %{--domainObject = grailsApplication.domainClasses.find {it.clazz.simpleName == "Runner"}.clazz.newInstance()--}%
                %{--domainClass = grailsApplication.getDomainClass(domainObject.class.name)--}%
                %{--%>--}%
            %{--<f:table collection="${runnerList}" properties="${domainClass.persistentProperties*.name}"/>--}%
            %{--<f:table collection="${runnerList}" properties="${domainClass.persistentProperties*.name}"/>--}%
            %{--<f:table collection="${runnerList}" />--}%
            <f2:table collection="${runnerList}" maxProperties='20' />

            <div class="pagination">
                <g:paginate total="${runnerCount ?: 0}" />
            </div>
        </div>
    </body>
</html>