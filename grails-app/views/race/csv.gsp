<%@ page import="org.sample.Race" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'race.label', default: 'race')}" />
    <title><g:message code="default.csv.label" args="[entityName]"/> </title>
    <asset:javascript src="csv.js"/>
    <asset:stylesheet src="csv.css"/>
</head>
<body>

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
<script>
    var csv;
    var importStatus = [];

    $(function() {
        // Handler for .ready() called.
        // on change we will activate the parse button
        $('#myId').change(function(){
            $('#parseBtn').prop('disabled', false);
        });
        // Parse local CSV files
        var conf = {columns:[
            { data: "name",         title: "Name" },
            { data: "startDate",    title: "StartDate" },
            { data: "city",         title: "City" },
            { data: "state",        title: "State" },
            { data: "distance",     title: "Distance" },
            { data: "cost",         title: "Cost" },
            { data: "maxRunners",   title: "MaxRunners" },
            { data: "attr1",        title: "attr1" },
            { data: "attr2",        title: "attr2" },
            { data: "attr3",        title: "attr3" },
            { data: "attr4",        title: "attr4" },
            { data: "attr5",        title: "attr5" },
            { data: "importStatus", title: "Import Status", defaultContent:""}
        ]};

        parseCsv(conf);

        $('#importBtn').click(function(){
            var myData = csv();
            // we disable this button not to be clicked one more time
            $(this).prop('disabled', true);
            _.each(myData, function(element, index, list){
                saveItemWithJson(ctx+"/race/savej",element);
            });
        });

        $('#showImportBtn').click(function(){
            renderTable(conf,importStatus);
            // we disable this button not to be clicked one more time
            $(this).prop('disabled', true);
        });
    });
</script>
</body>
</html>