"use strict";
var App = angular.module('Ardent', [ 'ui.bootstrap' ]).factory(
		'MyHttpInterceptor', function($q) {
			return {
				request : function(config) {
					$('#loading-id').show();
					return config || $q.when(config);
				},

				requestError : function(rejection) {
					$('#loading-id').hide();
					return $q.reject(rejection);
				},

				// On response success
				response : function(response) {
					$('#loading-id').hide();
					return response || $q.when(response);
				},

				// On response failture
				responseError : function(rejection) {
					$('#loading-id').hide();
					return $q.reject(rejection);
				}
			};
		})

.config(function($httpProvider) {
	$httpProvider.interceptors.push('MyHttpInterceptor');
});

App.controller('AppController', function($scope, $http) {
	console.log("in app controler");
	$scope.init = function() {
		$scope.pageno = 0;
		$scope.jobType = "All";
		$scope.labourCat = true;
		$http.post('/getAllJobsOnlogin/'+$scope.pageno+'/'+$scope.jobType+'/'+$scope.sortName).success(
				function(data) {
					$scope.jobsData = data.jobs;
					console.log("$scope.jobsData"
							+ JSON.stringify($scope.jobsData))
					$scope.totalJobs = data.jobsCount;
					if (data.jobsCount <= 10) {
						$('#next').hide();
						$('#next1').hide();
						$scope.JobPre = data.jobsCount;
					} else {
						$('#next').show();
						$('#next1').show();
						$scope.JobPre = 10;
					}

					if ($scope.pageno <= 0) {
						$('#pre').hide();
						$('#pre1').hide();
					} else {
						$('#pre').show();
						$('#pre1').show();
					}
					console.log("all position:"
							+ JSON.stringify($scope.jobsData));
					// $scope.username = data.uname;
				});

	}

	$scope.nextCount = 10;
	$scope.clickNext = function() {
		console.log("nexdt");
		$scope.pageno++;
		$scope.nextCount = parseInt($scope.nextCount) + 10;
		var count = 0;
		$http.post('/getAllJobsOnlogin/'+$scope.pageno+'/'+$scope.jobType+'/'+$scope.sortName).success(
				function(data) {
					$scope.jobsData = data.jobs;
					count = parseInt(data.jobsCount);
					console.log("count" + count);
					console.log("$scope.JobPre" + $scope.JobPre);
					if (data.jobsCount <= 10) {
						$('#next1').hide();
						$('#next').hide();
						$scope.JobPre = parseInt($scope.totalJobs);
					} else {
						$('#next1').show();
						$('#next').show();
						//	$scope.JobPre= 10;
						$scope.JobPre = parseInt($scope.JobPre) + 10;
					}
				});

		if ($scope.pageno <= 0) {
			$('#pre').hide();
			$('#pre1').hide();
		} else {
			$('#pre').show();
			$('#pre1').show();
		}
		if (count > 10) {
			$scope.nextCount = parseInt($scope.nextCount) + 10;
		}
	}

	$scope.clickPre = function() {
		// $scope.position = "notSelected";
		$scope.pageno--;
		var count = 0;

		
		$http.post('/getAllJobsOnlogin/'+$scope.pageno+'/'+$scope.jobType+'/'+$scope.sortName).success(
				function(data) {
					$scope.jobsData = data.jobs;

					$scope.count = parseInt(data.jobsCount);
					console.log("count" + count);
					//$scope.JobPre = count - 10;

					if (data.jobsCount <= 10) {
						$('#next').hide();
						$('#next1').hide();
						//$scope.JobPre = data.jobsCount;
						$scope.JobPre = parseInt($scope.nextCount) - 10;
					} else {
						$('#next').show();
						$('#next1').show();
						//$scope.JobPre  = 10;
						$scope.JobPre = parseInt($scope.nextCount) - 10;
					}

					if ($scope.nextCount > 10) {
						$scope.nextCount = parseInt($scope.nextCount) - 10;
					}

				});
		if ($scope.pageno <= 0) {
			$('#pre').hide();
			$('#pre1').hide();
		} else {
			$('#pre').show();
			$('#pre1').show();
		}
	}

	
	  $scope.sortBy = false;
	  $scope.sortName = "AllAsc";
	  $scope.getAllJobBySelectedElement = function(){
		 $http.post('/getAllJobsOnlogin/'+$scope.pageno+'/'+$scope.jobType+'/'+$scope.sortName)
			.success(function(data) {
				$scope.jobsData = data.jobs;
				$scope.totalJobs = data.jobsCount;
				if (data.jobsCount <= 10) {
					$('#next').hide();
					$('#next1').hide();
					$scope.JobPre = data.jobsCount;
				} else {
					$('#next').show();
					$('#next1').show();
					$scope.JobPre = 10;
				}

				if ($scope.pageno <= 0) {
					$('#pre').hide();
					$('#pre1').hide();
				} else {
					$('#pre').show();
					$('#pre1').show();
				}
			});
	  }
	
	  $scope.getAllJobByType = function(){
		  $http.post('/getAllJobsOnlogin/'+$scope.pageno+'/'+$scope.jobType+'/'+$scope.sortName)
			.success(function(data) {
				$scope.jobsData = data.jobs;
				$scope.totalJobs = data.jobsCount;
				if (data.jobsCount <= 10) {
					$('#next').hide();
					$('#next1').hide();
					$scope.JobPre = data.jobsCount;
				} else {
					$('#next').show();
					$('#next1').show();
					$scope.JobPre = 10;
				}

				if ($scope.pageno <= 0) {
					$('#pre').hide();
					$('#pre1').hide();
				} else {
					$('#pre').show();
					$('#pre1').show();
				}
			});
	  }
	  
	$scope.editJob;
	$scope.showJobDetails = function(jobs) {
		$scope.editJob = jobs;
		$('#jobDetails').modal();

	}
	$scope.applyforJobCheck = function() {
		$('#applyJobPopupMsg').modal();
	}

	$scope.openContactAdminForm = function() {
		$('#contactAdmin').modal();

	}

	$scope.sent = false;
	$scope.contactus = {};
	$scope.feedbackSuccess = false;
	$scope.onfeedBackClicked = function() {
		$('#contactAdmin').modal('hide');
		$http.post('/sendFeedbackMail', {
			contactus : $scope.contactus
		}).success(function(data) {
			console.log("success");
			$scope.feedbackSuccess = true;
			$scope.sent = true;
		});
	}

	$scope.initDatePicker = function() {

		$('#datepicker').datepicker({
			format : 'mm-dd-yyyy'
		});

	}

	$scope.initduetoGovtDatePicker = function() {

		$('#duetoGovt').datepicker({
			format : 'mm-dd-yyyy'
		});

	}

	$scope.initduetoPmoDatePicker = function() {

		$('#duetoPmo').datepicker({
			format : 'mm-dd-yyyy'
		});

	}

	$scope.forget_pass = false;
	$scope.getForgetPassword = function() {
		$http.get('/forgetPassword/' + $scope.useremail).success(
				function(data) {
					$scope.forget_pass = true;
					console.log("success");
					// $scope.username = data.uname;
				});
	}
});
