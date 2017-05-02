<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'registration.label', default: 'Registration')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-registration" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span> <g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-registration" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--<f:table collection="${registrationList}" />--}%
            <g:form role="form" url="[controllerName:'registration',action:'filtering']">
                <table  class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'registration.id.label', default: 'ID')}" />
                            <th><g:message code="registration.race.label" default="Race" /></th>
                            <th><g:message code="registration.runner.label" default="Runner" /></th>
                            <g:sortableColumn property="paid" title="${message(code: 'registration.paid.label', default: 'Paid')}" />
                            <g:sortableColumn property="dateCreated" title="${message(code: 'exitQueueConfig.dateCreated.label', default: 'Date Created')}" />
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><g:submitButton name="filtering" value="?" title="Filter"/> </td>
                            <td><g:textField size="20" name="race" placeholder="Race" title="Race" value="${filter?.race}"/></td>
                            <td><g:textField name="runner" size="20" placeholder="Runner" title="Runner" value="${filter?.runner}"/></td>
                            <td><g:textField name="paid" size="5" placeholder="Paid" title="Paid" value="${filter?.paid}"/></td>
                            <td></td>
                        </tr>
                        <g:each in="${registrationList}" status="i" var="bean">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td><g:link action="show" id="${bean.id}">${fieldValue(bean: bean, field: "id")}</g:link></td>
                                <td>${fieldValue(bean: bean, field: "race")}</td>
                                <td>${fieldValue(bean: bean, field: "runner")}</td>
                                <td>${fieldValue(bean: bean, field: "paid")}</td>
                                <td><g:formatDate date="${bean.dateCreated}" /></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:form>

            <div class="pagination">
                <g:paginate total="${registrationInstanceCount ?: 0}" />
            </div>
        </div>
    </body>
</html>