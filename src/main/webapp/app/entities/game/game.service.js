(function() {
    'use strict';
    angular
        .module('hlorkaApp')
        .factory('Game', Game);

    Game.$inject = ['$resource'];

    function Game ($resource) {
        var resourceUrl =  'api/games/:id';

        return $resource(resourceUrl, {id: '@id'}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'join': {
                method: 'PUT',
                url: 'api/games/:id/join'
            }
        });
    }
})();
