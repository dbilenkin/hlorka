(function () {
    'use strict';

    angular
        .module('hlorkaApp')
        .controller('JhiTrackerController', JhiTrackerController);

    JhiTrackerController.$inject = ['$cookies', '$http', 'JhiTrackerService', '$state'];

    function JhiTrackerController ($cookies, $http, JhiTrackerService, $state) {
        // This controller uses a Websocket connection to receive user activities in real-time.
        var vm = this;

        vm.activities = [];

        JhiTrackerService.receive().then(null, null, function(activity) {
            showActivity(activity);
        });

        function showActivity(activity) {
            var existingActivity = false;
            for (var index = 0; index < vm.activities.length; index++) {
                if(vm.activities[index].sessionId === activity.sessionId) {
                    existingActivity = true;
                    if (activity.page === 'join') {
                        $state.go('game');
                    }


                    if (activity.page === 'logout') {
                        vm.activities.splice(index, 1);
                    } else {
                        vm.activities[index] = activity;
                    }
                }
            }
            if (!existingActivity && (activity.page !== 'logout')) {
                vm.activities.push(activity);
            }
        }

    }
})();
