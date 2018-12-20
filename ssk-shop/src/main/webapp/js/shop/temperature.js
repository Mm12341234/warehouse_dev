var myChart = echarts.init(document.getElementById('left'));
var names=[];    //类别数组（实际用来盛放X轴坐标值）
var nums=[];    //销量数组（实际用来盛放Y坐标值）
var itemID;

//页面加载后，进行温度折线图加载
$(function(){
    // Start();
})

//开始加载
function Start(){
    getTemperatureMap();
    getNewFoodTemperature();
}

//获取前十分钟的温度折线图
function getTemperatureMap(){
    myChart.setOption({
        title: {
            text: '温度变化图',
            subtext:'当前十分钟内',
            textStyle: {
                fontStyle: 'normal',
                fontSize: 30
            }
        },
        tooltip: {
            //坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
            trigger: 'axis'
        },
        legend: {
            data:['温度']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} °C'
            }
        },
        series: [{
            name: '食品',
            type: 'line',
            data: []
        }]
    });
    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    $.ajax({
        // type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "/foodtemperature/queryTemperature/"+2,    //请求发送到TestServlet处
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            result=result.list;
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for(var i=result.length-1; i>=0; i--){
                    //时间戳转换成正常的日期
                    var t=new Date(result[i].temperaturetime);
                    names.push(t);    //挨个取出类别并填入类别数组
                }
                for(var i=result.length-1; i>=0; i--){
                    nums.push(result[i].foodtemperature);    //挨个取出销量并填入销量数组
                }
                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {
                        data: names
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '',
                        data: nums,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        //显示平均温度
                        markLine: {
                            data: [
                                {type: 'average', name: 'average'}
                            ]
                        }
                    }]
                });

            }
        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })
}

//获取最新温度折线图
function getNewFoodTemperature(){
    $.ajax({
        async: true,
        url : "/foodtemperature/queryTemperature/"+2,
        dataType : "json",
        success : function (result) {
            result=result.list;
            if (result) {
                //获取返回的时间，并转换时间格式
                var time = new Date(result.temperaturetime);
                //将获取的时间存入name数组，放在数组的最后一个位置
                moveArrayElement(names,time);

                //获取返回的最新温度
                var newFoodTemperature = result.foodtemperature;
                //将获取的温度存入nums数组，放在数组的最后一个位置
                moveArrayElement(nums,newFoodTemperature);

                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {
                        data: names
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '',
                        data: nums,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        //显示平均温度
                        markLine: {
                            data: [
                                {type: 'average', name: 'average'}
                            ]
                        }
                    }]
                });
            }
        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    });

    //定时器循环执行
    itemID=setTimeout("getNewFoodTemperature();", 10000);
}

//移动数组元素，更新温度数组和时间数组（去除第一个数组元素，加入最后一个数组元素）
function moveArrayElement(array,value){
    for(var i=0; i<array.length;i++){
        array[i] = array[i+1];
    }
    array[array.length-1] = value;
    //console.log(array[array.length-1]);
}

//发送自定义的温度到数据库
// function postFoodTemperature(){
//     var foodTemperature = $("#newtemperature input[name='temperature']").val();
//     //alert(foodTemperature);
//     $.ajax({
//         type: "post",
//         async: true,
//         url: "/Foods/insertFoodTemperature",
//         data: {foodTemperature:foodTemperature},
//         success : function (result) {
//             if(result == 1){
//                 alert("添加成功！");
//             }
//         }
//     });
// }

//开启定时器，加载温度折线图
function startTime() {
    //清除names,nums数组元素，重新加载元素
    names.length = 0;
    nums.length = 0;
    //加载温度折线图
    Start();
}

//停止定时器
function stopTime() {
    clearTimeout(itemID);
}

//返回
// function setReturn(){
//     // alert(vm.id);
//     $('#showListThree').hide();
//     vm.showListTwo=false;
//     vm.showListOne=true;
//     clearTimeout(itemID);
// }