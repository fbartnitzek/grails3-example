<ul class="nav nav-pills">
    <li><a class="home" href="${createLink(uri: '/')}"><span class="glyphicon glyphicon-home"></span> <g:message code="default.home.label"/></a></li>
    <li><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span> <g:message code="default.list.label" args="[entityName]" /></g:link></li>
    <li><a class="home" href="${createLink(controller: 'race',action: 'csv')}"><span class="glyphicon glyphicon-refresh"></span> <g:message code="refresh"/></a></li>
</ul>
<h2 class="headLine"><g:message code="default.csv.label" args="[entityName]" /></h2>
<div class="form-inline">
    <label>1. <g:message code="csv.step1.label"/>:</label>
    <form action="#">
        %{--<span class="btn btn-default btn-file">--}%
        <input type="file" name="myFile" id="myId">
        %{--</span>--}%
    </form>
    <p>&nbsp;</p>
    <button id="parseBtn" class="btn btn-primary" disabled="disabled">2. <g:message code="csv.step2.label"/></button>
    <p>&nbsp;</p>
    <button id="importBtn" class="btn btn-primary" disabled="disabled">3. <g:message code="csv.step3.label"/></button>
    <p>&nbsp;</p>
    <button id="showImportBtn" class="btn btn-primary" disabled="disabled">4. <g:message code="csv.step4.label"/></button>
</div>
<p>&nbsp;</p>
<hr/>
<!-- here will be the table rendered! -->
<div id="demo"></div>
