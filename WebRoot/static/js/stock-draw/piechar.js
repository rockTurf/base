$(function () {

    var data = [];
    var series = Math.floor(Math.random()*10)+1;
    for( var i = 0; i<series; i++)
    {
        data[i] = { label: "Series"+(i+1), data: Math.floor(Math.random()*100)+1 }
    }

    $.plot($("#pie-chart"), data,
    {
        series: {
            pie: {
                show: true,
                radius: 1,
                label: {
                    show: true,
                    radius: 3/4,
                    formatter: function(label, series){
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
                    },
                    background: { opacity: 0 }
                }
            }
        },
        grid: {hoverable: true},
        legend: {
            show: false
        }
    }); 

    $.plot($("#pie-chart2"), data,
    {
        series: {
            pie: {
                show: true
            }
        },
        grid: {hoverable: true}
    });


    $.plot($("#pie-chart3"), data,
    {
        series: {
            pie: {
                show: true
            }
        },
        grid: {hoverable: true},
        legend: {
            show: false
        }
    });   

/* Pie chart ends */

});