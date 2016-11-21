$(document).ready(function() {
	var formId = $("#form_id").attr("data-form-id");
    $.ajax({
        type: "POST",
        url: "statistics",
        data: {
            action: "getStatistics",
            form_id: formId
        },
        success: function(data) {
        	var obj = JSON.parse(data);
       
        	Object.keys(obj).map(function(key, index) {
        		return obj[key] = JSON.parse(obj[key]);
    		});
        	
        	var configFormElements = ["select", "radio-group", "textarea", "text", "hidden"];
        	
        	// Formular Elemente aus kompletten Formular JSON extrahieren
        	var extractFormFields = function(formJson) {
        	    var deferred = new $.Deferred();
        		var formElements = {};
        		
        		$.each(formJson, function(key, value) {
        			if (configFormElements.indexOf(value.type) !== -1) {
        				formElements[value.name] = value;
        			}
        			deferred.resolve(formElements);
        		})
        		return deferred.promise();
        	};
        	
        	// Enthaltene Formular Response Elemente extrahieren
        	var getFormResponseElements = function(resultsJson) {
        		var FormElements = [];
        	    var deferred = new $.Deferred();
        	            		
        		$.each(resultsJson, function(key, value) {
        			var formElement = JSON.parse(value.value); 
        			$.each(formElement, function(name, response) {
        				if ($.inArray(name, FormElements) === -1) {
        					FormElements.push(name);
        				}
            		})
            		deferred.resolve(FormElements);
        		})
        		return deferred.promise();
        	}
        	
        	var formElements = {};
        	
        	obj.formJson.map(function(value, key) {
        		if (value.values !== undefined) {
        			formElements[value.name] = value.values;
        		}
        	});
        	
        	var chartConfigData = {};
        	chartConfigData.rows = {};
        	
        	getFormResponseElements(obj.resultsJson)
        	.then(function(formResponseElements) {
        		var rows = {};
        		for (var i = 0; i < formResponseElements.length; i++) {
        			var element = formResponseElements[i];
        			if (formElements[element] !== undefined) {
        				chartConfigData.rows[element] = formElements[element]; 
        			}
        		}
        		
        		$.each(obj.resultsJson, function(key, answers) {
        			var answerCount = {};
        			var answerObj = JSON.parse(answers.value);

        			$.each(answerObj, function(name, value) {
        				console.log(name);
        				if (answerCount[name] === undefined) {
        					answerCount[name] = 1;
        				}
        				else {
        					answerCount[name] = answerCount[name] + 1;
        				}
        			})
        			
        			chartConfigData.columns = answerCount; 
        			
        		})
        		
        		console.log(chartConfigData);
        		
        	});
        	
//        	console.log(obj.resultsJson);
        	
        	
        	
//        	var buildColumns = function(elements) {
//        	    var deferred = new $.Deferred();
//        		
//        	    $.each(elements.values, function(key, value) {
//        	    	console.log(value.value);
//        	    })
//        	    
////        	    console.log(elements.values);
//        	    
////    			deferred.resolve(formElements);
////    		return deferred.promise();
//        	}

//        	extractFormFields(obj)
//        	.then(function(formElements) {
//        		var columns = [];
//        		var rows = [];
//        		console.log(formElements);
//        	});
//
//        		$.each(formElements, function(key, value) {
//        			
////        			console.log(key);
//        		})
////        		console.log("promise Data", formElements);
//        	})
        	
//        	console.log("results", obj.resultsJson);
        	
        	
//        	console.log(extractFormFields(obj));
        	
        	
        	function drawCircleChart() {
	        		   
	        	// Load the Visualization API and the corechart package.
	        	google.charts.load('current', {'packages':['corechart']});
	        	
	        	// Set a callback to run when the Google Visualization API is loaded.
	            // Callback that creates and populates a data table,
	            // instantiates the pie chart, passes in the data and
	            // draws it.
	        	google.charts.setOnLoadCallback(drawChart);
	            
	            function drawChart() {
	
	                // Create the data table.
	                var data = new google.visualization.DataTable();
	
	                data.addColumn('string', 'Name');
	                data.addColumn('number', 'Value');
	
	            	data.addRows([
	            		['Onions', 1],
	            		['Mushrooms', 3],
	            		['Olives', 1],
	            		['Zucchini', 1],
	            		['Pepperoni', 2]
	        		]);
	                
	
	                // Set chart options
	                var options = {
	            		'title' : 'So verlief die Abstimmung',
	            		'width' : 400,
	            		'height' : 300
	               };
	
	                // Instantiate and draw our chart, passing in some options.
	                var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	            	chart.draw(data, options);
	          	}
	    	}
            
            
//            function drawChart() {
//
//              // Create the data table.
//              var data = new google.visualization.DataTable();
//
//              data.addColumn('string', 'Topping');
//              data.addColumn('number', 'Slices');
//              data.addRows([
//                ['Mushrooms', 3],
//                ['Onions', 1],
//                ['Olives', 1],
//                ['Zucchini', 1],
//                ['Pepperoni', 2]
//              ]);
//
//              // Set chart options
//              var options = {'title':'So verlief die Abstimmung',
//                             'width':400,
//                             'height':300};
//
//              // Instantiate and draw our chart, passing in some options.
//              var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
//              chart.draw(data, options);
//            }
  
        },
        error: function(data) {
        	console.log("error", data);
        }
    });
})