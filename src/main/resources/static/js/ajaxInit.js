$.ajaxSetup({
    complete :
        function(xhr, status) {
            var REDIRECT = xhr.getResponseHeader("REDIRECT");
            if (REDIRECT == "REDIRECT") {
                var win = window;
                while (win != win.top) {
                    win = win.top;
                }
                win.location.href = xhr.getResponseHeader("CONTEXTPATH");
            }
        },
    beforeSend: function(request) {
        request.setRequestHeader("token", $.cookie('token'));
    },
})