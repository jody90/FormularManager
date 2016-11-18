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
        	console.log("data", obj);

        	 // Load the Visualization API and the corechart package.
            google.charts.load('current', {'packages':['corechart']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            
            function getColumns(obj, callback) {
            	var data = {};
            	var columns = {};
            	var rows = {};
                $.each(obj, function(k, v) {
				  var valueObj = JSON.parse(v.value);
				  $.each(valueObj, function(key, value) {
					  if (!(key in columns)) {
						  columns[key] = typeof(key);
					  }
					  
					  if (!(valueObj[key] in rows)) {
						  rows[valueObj[key]] = 1;
					  }
					  else {
						  rows[valueObj[key]] = rows[valueObj[key]] + 1;
					  }
				  })
                })
//                console.log(rows);
//                console.log(columns);
//                data.push(rows);
//                data.push(columns);
                callback(data);
            }
            
            getColumns(obj, function(data) {
            	console.log(data);
            })
            
            function drawChart() {

              // Create the data table.
              var data = new google.visualization.DataTable();

              data.addColumn('string', 'Topping');
              data.addColumn('number', 'Slices');
              data.addRows([
                ['Mushrooms', 3],
                ['Onions', 1],
                ['Olives', 1],
                ['Zucchini', 1],
                ['Pepperoni', 2]
              ]);

              // Set chart options
              var options = {'title':'So verlief die Abstimmung',
                             'width':400,
                             'height':300};

              // Instantiate and draw our chart, passing in some options.
              var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
              chart.draw(data, options);
            }
  
        },
        error: function(data) {
        	console.log("error", data);
        }
    });
})