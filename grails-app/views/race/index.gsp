<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'race.label', default: 'Race')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-race" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-race" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <f2:table collection="${raceList}" maxProperties="20"/>
            %{--<f:table collection="${raceList}"/>--}%
            %{--<f:table collection="${raceList}" properties="['attr1','attr2','attr3','attr4','attr5','city','cost','distance','maxRunners','name','registrations','startDate','state']"/>--}%

            <div class="pagination">
                <g:paginate total="${raceCount ?: 0}" />
            </div>
        </div>
    </body>
</html>