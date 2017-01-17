(function() {
    'use strict';

    angular
        .module('hlorkaApp')
        .controller('GameController', GameController);

    GameController.$inject = ['$scope', '$state', 'Game', 'JhiTrackerService'];

    function GameController ($scope, $state, Game, JhiTrackerService) {
        var vm = this;

        vm.games = [];

        JhiTrackerService.receive().then(null, null, function(game) {
            loadAll();

        });

        vm.join = join;

        function join(id) {
            Game.join({id: id});
        }

        loadAll();

        function loadAll() {
            Game.query(function(result) {
                vm.games = result;
                vm.searchQuery = null;
            });
        }
    }
})();
