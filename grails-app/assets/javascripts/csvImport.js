// globals: famo2,
// since we use here ajax, we need support for it.
$(function() {
    $.extend(grailsExample, (function () {
        var pleaseWaitDiv = $(
            '<div class="modal fade" id="pleaseWaitDialog" role="dialog" aria-labelledby="pleaseWait" data-backdrop="static" data-keyboard="false">' +
                '<div class="modal-dialog" role="document">' +
                    '<div class="modal-content">'+
                        '<div class="modal-header">' +
                            '<h1>Processing...</h1>' +
                        '</div>' +
                        '<div class="modal-body">' +
                            '<div class="progress">' +
                                '<div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar"  style="width: 100%;">' +
                                    '<span class="sr-only">&nbsp;</span>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</div>'
        );
        return {
            ajaxCount:0,
            what:null,
            showPleaseWait: function() {
                if (!(pleaseWaitDiv.data('modal') && pleaseWaitDiv.data('modal').isShown)) {
                    $.event.trigger({
                        type: "modalShow",
                        message: "Modal Window was shown!",
                        time: new Date()
                    });
                    pleaseWaitDiv.modal('show');
                }
            },
            hidePleaseWait: function () {
                $.event.trigger({
                    type: "modalClose",
                    message: "Modal Window was closed!",
                    time: new Date()
                });
                pleaseWaitDiv.modal('hide');
            }
        };
    })());
    // if the modal window was closed, we display the results
    $(document).on("modalClose", function(){
        $('#showImportBtn').prop('disabled', false);
    });
});
function parseCsv(conf) {
    $('#parseBtn').click(function(){
        $('#myId').parse({
            config: {
                header: true,
                //dynamicTyping:false,
                complete: function (results) {
                    //console.log("Parse results:", results.data);
                    //console.log("Parse results...:");
                    //console.dir(results.data);
                    // results.data[2] = _.extend(results.data[2], {"err":"Project does not exist!"});
                    // remove the first element since PapaParse can't seem to get rid of it.
                    var myDisable = function() {
                        $('#parseBtn').prop('disabled', true);
                        $('#importBtn').prop('disabled', false);
                    };
                    renderTable(conf,results.data, myDisable);
                    csv = function(){ return results.data}; // we give a reference out.
                }
            },
            step: function(row) {
                console.log("Row:", row.data);
            },
            error: function(err, file, inputElem, reason) {
                // executed if an error occurs while loading the file,
                // or if before callback aborted for some reason }
                console.log("Error happened!");
            },
            complete: function() {
                console.log("All files done....!");
            }
        });
    });
}

function saveItemWithJson(url,item){
    $.ajax({
        type: "POST",
        url:url,
        dataType: "json",
        data: item,
        beforeSend: function( xhr ) {
            grailsExample.ajaxCount++; // we increase the counter
            grailsExample.showPleaseWait();
        },
        success: function(){ console.log('Success ...'); }
    }).done(function(data) {
        // alert( "second success" );
        console.log(data);
        var msg;
        if(data.success){
            msg = "OK!";
            if(data.msg) { msg+=" "+data.msg}
        } else {
            // the was an error not rendered as correct ajax error!
            msg = "Error!";
            if(data.msg) { msg+=" "+data.msg}
        }


        importStatus.push(_.extend(item, {importStatus: msg}));
        // return true;
    }).fail(function(jqXHR, textStatus, errorThrown) {
        // alert( "error" );
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
        var errorMessage = jqXHR.resposeText;
        if(jqXHR.responseJSON) {
            errorMessage = jqXHR.responseJSON.msg ? jqXHR.responseJSON.msg : jqXHR.resposeText;
        }
        importStatus.push(_.extend(item, {importStatus: (errorMessage ? errorMessage : errorThrown)}));
        // return errorThrown;
    }).always(function( ) {
        if(grailsExample.ajaxCount > 0) {
            grailsExample.ajaxCount--;
        }
        if(grailsExample.ajaxCount == 0) {
            grailsExample.hidePleaseWait();
        }
    });
}

function renderTable(conf, dataSet, endCallback){
    $('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display, compact, hover, row-border, nowrap, order-column" id="example"></table>' );

    $('#example').dataTable( {
        "data": dataSet,
        "order": [[ 1, "asc" ]],
        "columns": conf.columns
    } );

    if(endCallback) {
        console.log("end Callback will execute");
        endCallback();
    }
}
