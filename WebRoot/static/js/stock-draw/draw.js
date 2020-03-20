  $("#draw-button").click(function(){
        stock_id = $('#stockSelect').val();//股票
        search_time = $('#search_time').val();//时间
        type = $('#SelectType').val();//图形类型
        $.ajax({
            type: "POST",
            url: 'stockDraw/draw',
            data: {
                stock_id:stock_id,
                search_time:search_time,
                type:type
            },
            dataType:'json',
            cache: true,
            success: function(data){
                console.info('data.success=='+data.success);
                //判断一下是否有数据
                if(data.success!='true'){
                    //显示无数据div
                    document.getElementById('dataEmpty').style.display='block';
                    document.getElementById('container').style.display='none';
                    //清空日期
                    $('#search_time').val('');
                    $('#form_datetime').datetimepicker('update');
                }else{
                    //隐藏无数据div，开始绘图
                    document.getElementById('container').style.display='block';
                    document.getElementById('dataEmpty').style.display='none';
                    //更新日期
                    $('#search_time').val(data.search_time);
                    $('#form_datetime').datetimepicker('update');
                    drawChar1(data);
                }

            }
        });
    });




    function drawChar1(data){
        var priceArray = data.priceArray;
        var BArray = data.BArray;
        var SArray = data.SArray;
        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        app.title = '正负条形图';

        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:['买入', '卖出']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'value'
                }
            ],
            yAxis : [
                {
                    type : 'category',
                    axisTick : {show: false},
                    //data : ['周一','周二','周三','周四','周五','周六','周日']
                    data : priceArray
                }
            ],
            series : [
                {
                    name:'买入',
                    type:'bar',
                    label: {
                        normal: {
                            show: false
                        }
                    },
                    //data:[200, 170, 240, 244, 200, 220, 210]
                    data : BArray
                },
                {
                    name:'卖出',
                    type:'bar',
                    stack: '总量',
                    label: {
                        normal: {
                            show: false
                        }
                    },
                    //data:[-120, -132, -101, -134, -190, -230, -210]
                    data : SArray
                }
            ]
        };

        myChart.on('brushSelected', renderBrushed);

        myChart.resize({
            height : '600'
        })

        function renderBrushed(params) {
            var brushed = [];
            var brushComponent = params.batch[0];

            for (var sIdx = 0; sIdx < brushComponent.selected.length; sIdx++) {
                var rawIndices = brushComponent.selected[sIdx].dataIndex;
                brushed.push('[Series ' + sIdx + '] ' + rawIndices.join(', '));
            }

            myChart.setOption({
                title: {
                    backgroundColor: '#333',
                    text: 'SELECTED DATA INDICES: \n' + brushed.join('\n'),
                    bottom: 0,
                    right: 0,
                    width: 100,
                    textStyle: {
                        fontSize: 12,
                        color: '#fff'
                    }
                }
            });
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }