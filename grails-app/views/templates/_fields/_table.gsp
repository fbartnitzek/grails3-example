%{-- default: https://github.com/grails-fields-plugin/grails-fields/blob/master/grails-app/views/templates/_fields/_table.gsp--}%
<table>
    <thead>
    <% def props = domainProperties*.name
//       println(props)
       props.removeAll{['password'].contains(it)}
//       println("<br/>")
//       println(props)
    %>
    <tr>
        <g:each in="${props}" var="p" status="i">
            <g:set var="propTitle">${domainClass.propertyName}.${p}.label</g:set>
            <g:set var="propNaturalName">${domainClass.getPersistentProperty(p as String).naturalName}</g:set>
            <g:sortableColumn property="${p}" title="${message(code: propTitle, default: propNaturalName)}" />
        </g:each>
    </tr>
    </thead>
    <tbody>
    %{-- no real each - already reduced --}%
    <g:each in="${collection}" var="bean" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <g:each in="${props}" var="p" status="j">
                <g:if test="${j==0}">
                    <td><g:link method="GET" resource="${bean}">
                        %{--<f:display bean="${bean}" property="${p}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/>--}%
                        <g:fieldValue bean="${bean}" field="${p}"/>
                    </g:link></td>
                </g:if>
                <g:else>
                    <td><f:display bean="${bean}" property="${p}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                </g:else>
            </g:each>
        </tr>
    </g:each>
    </tbody>
</table>