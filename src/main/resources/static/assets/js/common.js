$(function () {
    'use strict';

    var App = {
        init: function () {
            this.tabs();
        },
        tabs: function () {
            var tabId;
            var tabSelector = '#myTab';
            if('undefined' != typeof window['localStorage']) {
                if ( localStorage.getItem('idTab') ) {
                    tabId = localStorage.getItem('idTab');
                    $('#'+tabId).tab('show');
                } else {
                    $(tabSelector+' a:first').tab('show');
                }
                $(tabSelector+' a').live('click', function (e) {
                    e.preventDefault();
                    $(this).tab('show');
                    localStorage.setItem('idTab', e.currentTarget.id);
                });
            } else {
                $('#myTab a').live('click', function (e) {
                    e.preventDefault();
                    $(this).tab('show');
                });
            }

        }

    };

    App.init();

});