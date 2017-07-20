/**
 * Created by sk2rldnr on 2017-07-10.
 */
(function () {
    'use strict';

    angular.module('adminApp')
        .controller('CategoryCtrl', CategoryCtrl);

    CategoryCtrl.$inject = ['$http', '$scope'];

    function CategoryCtrl($http, $scope) {

        $scope.category = [];

        $scope.availabilityFilter = "all";
        $scope.firstDepthFilter = "전체";
        $scope.secondDepthFilter = "전체";

        $scope.init = init;
        $scope.updateWorkDate = updateWorkDate;
        $scope.checkWeight = checkWeight;
        $scope.synchronizeWithDB = synchronizeWithDB;
        $scope.downloadAsCSV = downloadAsCSV;
        $scope.isChanged = isChanged;
        $scope.typeToInt = typeToInt;

        $scope.availabilityStatuses = [
            {value: true, text: 'true'},
            {value: false, text: 'false'}
        ];


        init();


        function init() { // DB data to angular from server
            var url = "/category/";
            var categoryData = $http.get(url);

            categoryData.then(function success(response) {
                $scope.category = response.data;
                console.log("초기화 성공");
            }, function error() {
                console.log("초기화 실패");
            });
        }


        function updateWorkDate(category) {
            category.workDate = new Date();
        }

        function typeToInt(category) {
            category.weight = parseInt(category.weight);
        }

        function isChanged(beforeChange, afterChange, category) {
            var isUpdated = beforeChange !== afterChange;
            if (isUpdated) {
                updateWorkDate(category);
            }
        }

        function checkWeight(beforeChange, weight, category) {

            var isNotInteger = Number(weight) !== parseInt(weight);
            var outOfRange = weight > 100 || weight < 0;
            var isUpdated = beforeChange !== weight;
            var weightIsValid = isNotInteger || isNaN(weight) || outOfRange;

            if (weightIsValid) {
                alert("0~100 사이의 값을 정수로 입력해 주세요");
                return false;
            }
            if (isUpdated) {
                updateWorkDate(category);
            }
        }

        function synchronizeWithDB() {
            var url = "/category/synchronize";
            $http.post(url, $scope.category).then(
                function success() {
                    console.log("DB 반영 성공");
                }, function error() {
                    console.log("DB 반영 실패");
                });
        }


        function downloadAsCSV() {
            var url = "/category/export";
            $http.get(url).then(
                function success() {
                    console.log("다운로드 성공");
                }, function error() {
                    console.log("다운로드 실패");
                });
        }

        $('#btn-upload').on('click', function () {
            var form = new FormData(document.getElementById('uploadForm'));

            var fileNotSelected = document.getElementById('fileId').files.length === 0;
            if (fileNotSelected)
                return false;

            $.ajax({
                url: "category/import",
                data: form,
                dataType: 'text',
                processData: false,
                contentType: false,
                type: 'POST',
                success: function (response) {
                    $scope.category = JSON.parse(response);
                    $scope.$apply();
                    console.log("업로드 성공");
                },
                error: function () {
                    console.log("업로드 실패");
                }
            });
        });

    }
})();