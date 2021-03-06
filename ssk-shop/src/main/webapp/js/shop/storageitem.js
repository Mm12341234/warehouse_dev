$(function () {
    $("#jqGrid").Grid({
        url: '../storageitem/list',
        multiselect: false,
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '交易单编号', name: 'no', index: 'no', width: 80},
			{label: '客户', name: 'customerName', index: 'customer_id', width: 80},
			{label: '分类', name: 'categoryName', index: 'category_id', width: 80},
			{label: '数量', name: 'num', index: 'num', width: 80},
			{label: '所在位置', name: 'shelvesId', index: 'shelves_id', width: 80,
				formatter(value,option,rowObject){
                    return rowObject.warehouseName+">"+rowObject.roomName+">"+rowObject.shelvesName;
				}
			},
			{label: '货架层数', name: 'shelvesNum', index: 'shelves_num', width: 80},
			{label: '入库方式', name: 'inType', index: 'in_type', width: 80,
				formatter(val){
				    if(val==0){
				    	return "自动";
					}else{
				    	return "手动";
					}
				}
			},
			{label: '审核员', name: 'examinerName', index: 'check_id', width: 80},
			{label: '出库方式', name: 'isNormal', index: 'is_normal', width: 80,
				formatter(val){
				    if(val==0){
				    	return "正常出库";
					}else{
				    	return "腐损出库";
					}
				}
			},
			{label: '入库时间', name: 'inStorageTime', index: 'finish_time', width: 80,
				formatter(value){
                    return transDate(value);
				}
			},
			{label: '出库时间', name: 'finishTime', index: 'finish_time', width: 80,
                formatter(value){
                    return transDate(value);
                }
			}]
    });

    //左边图形left
    var mainContainer = document.getElementById('left');
    var resizeMainContainer = function () {
        mainContainer.style.width = '500px';
        mainContainer.style.height ='500px';
    };
    //设置div容器高宽
    resizeMainContainer();
    // 初始化图表
    var myChart = echarts.init(mainContainer);
    $(window).on('resize',function(){//
        //屏幕大小自适应，重置容器高宽
        resizeMainContainer();
        myChart.resize();
    });

    //统计入库数据
    var url="/storageitem/queryAllNumGroupByWareHouse";
    $.ajax({
        async : true,               //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : url,                  //请求发送到TestServlet处
        dataType : "json",          //返回数据形式为json
        success : function(result) {
            result=result.list;
            if (result) {
                var names=[];
                var nums=[];
                for(var i=0;i<result.length;i++){
                    names[i]=result[i].name;
                    nums[i]=result[i].num;
                }
                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({
                    title: {
                        text: '存储明细统计',
                        subtext:'',
                        textStyle: {
                            fontStyle: 'normal',
                            fontSize: 15
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
                        data: names
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} 次'
                        }
                    },
                    series: [{
                        name: '食品',
                        type: 'line',
                        data: nums
                    }]
                });
                myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
                myChart.hideLoading();    //隐藏加载动画
            }
        },
        error : function(errorMsg) {
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })


    //右边图形right
    var rightContainer = document.getElementById('right');
    var resizeRightContainer = function () {
        rightContainer.style.width = '500px';
        rightContainer.style.height ='500px';
    };
    resizeRightContainer();
    var myRightChart = echarts.init(rightContainer);
    $(window).on('resize',function(){
        resizeRightContainer();
        myRightChart.resize();
    });

    //统计入库数据
    var url="/storageitem/queryAllNumGroupByWareHouse";
    $.ajax({
        async : true,               //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : url,                  //请求发送到TestServlet处
        dataType : "json",          //返回数据形式为json
        success : function(result) {
            result=result.list;
            if (result) {
                var rightData=[];
                var warehouseNameArray=[];
                for(var i=0;i<result.length;i++){
                    rightData.push({value:result[i].num,name:result[i].name});
                    warehouseNameArray[i]=result[i].name;
                }
                // console.log(rightData);
                // debugger
                myRightChart.hideLoading();    //隐藏加载动画
                myRightChart.setOption({
                    title: {
                        text: '存储明细统计',
                        subtext:'',
                        textStyle: {
                            fontStyle: 'normal',
                            fontSize: 15
                        }
                    },
                    legend: {
                        bottom: 10,
                        left: 'center',
                        data: warehouseNameArray
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{b} : {c}次 ({d}%)"
                    },
                    series : [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: '55%',
                            data:rightData
                        }
                    ]
                })
                myRightChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
                myRightChart.hideLoading();    //隐藏加载动画
            }
        },
        error : function(errorMsg) {
            alert("图表请求数据失败!");
            myRightChart.hideLoading();
        }
    });

});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		storageItem: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.storageItem = {};
		},
		update: function (event) {
            let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            let url = vm.storageItem.id == null ? "../storageitem/save" : "../storageitem/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.storageItem),
                type: "POST",
			    contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
			});
		},
		del: function (event) {
            let ids = getSelectedRows("#jqGrid");
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../storageitem/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
				    contentType: "application/json",
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
					}
				});
			});
		},
		getInfo: function(id){
            Ajax.request({
                url: "../storageitem/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.storageItem = r.storageItem;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                name: ''
            }
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        excelExport:function(){
            var str = encodeURI(vm.q.name);
            location.href='../currentfood/excelexport?sod='+str;
        },
	}
});