<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <script>
    var ctx = '${application.getContextPath()}';
    var grailsExample = grailsExample || {};
    </script>
    <g:layoutHead/>
</head>
<body>

    <nav class="navbar navbar-default">
        <div class="container-fluid">   <!-- bootstrap container class with full width (just container for fixed width) -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <sec:ifLoggedIn>
                        <li><a href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home"></span> home</a></li>
                        <sec:ifAnyGranted roles="ROLE_USER">
                            <li class="dropdown">   <!-- bootstrap dropdown -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Runner Setup<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="${createLink(controller: 'runner')}" title="Runner List"><span class="glyphicon glyphicon-check"></span> Runner List</a></li>
                                    <li><a href="${createLink(controller: 'registration')}" title="Registrations"><span class="glyphicon glyphicon-share"></span> Registrations</a></li>
                                </ul>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <li class="dropdown">
                                <a href="#" title="Master Dropdown" class="dropdown-toggle" data-toggle="dropdown">Administration<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="${createLink(controller: 'race')}"         title="Race List"><span class="glyphicon glyphicon-eye-open"></span> Race List</a></li>
                                    <li><a href="${createLink(controller: 'user')}"         title="User List"><span class="glyphicon glyphicon-eye-open"></span> User List</a></li>
                                    <li><a href="${createLink(controller: 'role')}"         title="Role List"><span class="glyphicon glyphicon-eye-open"></span> Role List</a></li>
                                    <li><a href="${createLink(controller: 'userRole')}"     title="UserRole List"><span class="glyphicon glyphicon-eye-open"></span> UserRole List</a></li>
                                    <li><a href="${createLink(controller: 'permission')}"   title="Permission List"><span class="glyphicon glyphicon-eye-open"></span> Permission List</a></li>
                                </ul>
                            </li>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <li class="dropdown">
                                <a href="#" title="Import Master Data" class="dropdown-toggle" data-toggle="dropdown">Import<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="${createLink(controller: 'race', action: 'csv')}"  title="Race CSV Import">${message(code: 'race.label', default:'Race')}</a></li>
                                </ul>
                            </li>
                        </sec:ifAnyGranted>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li><a href="${createLink(controller: 'login', action: 'auth')}"><span class="glyphicon glyphicon-user"></span> Login</a></li>
                    </sec:ifNotLoggedIn>
                    <li><a href="${createLink(uri:'/help/index.html')}" title="Help"><span class="glyphicon glyphicon-question-sign"></span></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>


</body>
</html>
