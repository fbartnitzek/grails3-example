<%@ page import="org.sample.Race" %>
<div class="form-group fieldcontain ${hasErrors(bean: registration, field: 'race', 'error')} required">
    %{--<label for="${property}">${label}<span class="required-indicator">*</span></label>--}%
    <label for="race" class="col-sm-2 control-label">
        Race<span class="required-indicator">*</span>
    </label>
    <div class="col-sm-10">
        %{--<g:select id="${property}" name="${property}" from="${Race.list(sort: 'name', order: 'asc')}" optionKey="id" required="" value="${registration?.race?.id}" class="many-to-one form-control"/>--}%
        %{--<g:select id="race" name="race" from="${Race.list(sort: 'name', order: 'asc')}" optionKey="id" required="" value="${registration?.race?.id}" class="many-to-one form-control"/>--}%
        <input id="race" name="race.id" type="hidden" value="${registration?.race?.id}"/>
    </div>
</div>

<script type="text/javascript">
    $('#race').select2({
        placeholder: "${message(code:'race.select')}",
        allowClear: true,
        minimumInputLength: 1,
        width: '300px',
        ajax: {
            url: ctx+'/race/listj',
            dataType: 'json',
            data: function(term, page) {
                return {
                    q: term,
                    page_limit: 10
                }
            },
            results: function(data, page) {
                return {results: data.resultList}
            }
        },
        initSelection: function(element, callback) {
            var id=$(element).val();
            if (id!=="") {
                $.ajax(ctx+"/race/showj/"+id, {
                    dataType: "json"
                }).done(function(data) { callback({id:data.id, text: data.name}); });
            }
        }
    });

</script>