(function() {
    'use strict';

    angular
        .module('hlorkaApp')
        .controller('GameController', GameController);

    GameController.$inject = ['$scope', '$state', 'Game'];

    function GameController ($scope, $state, Game) {
        var vm = this;

        vm.games = [];

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
