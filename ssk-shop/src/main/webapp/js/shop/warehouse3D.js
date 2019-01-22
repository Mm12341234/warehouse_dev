$(function(){
    vm.queryFloor();
})
let vm = new Vue({
	el: '#rrapp',
	data: {
	    warehouseId:null,
        showList: true,
        title: null,
        cityList:[],
        countryList:[],
        countryId:"",
        floorList:[],
        floor:0,//仓库的第几楼层
        sexList:[
            {id:0,name:"男"},
            {id:1,name:"女"}
        ],
		examiner: {},
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
			vm.examiner = {};
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

            let url = vm.examiner.id == null ? "../examiner/save" : "../examiner/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.examiner),
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
				    url: "../examiner/delete",
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
                url: "../examiner/info/"+id,
                async: true,
                successCallback: function (r) {
                    console.log(r);
                    vm.examiner = r.examiner;
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
        eyeImagePicUrl: function () {
            var url = vm.examiner.idcardfaceurl;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleSuccess: function (res, file) {
            vm.examiner.idcardfaceurl= file.response.url;
        },
        lookimg: function (url) {
            eyeImage(url);
        },
        //根据仓库的id，查看仓库有多少层
        queryFloor:function(){
            Ajax.request({
                url: "../warehouse/info/"+vm.warehouseId,
                async: true,
                successCallback: function (r) {
                    console.log(r);
                    vm.floorList=r.warehouse.floor;

                }
            });
        },
        //重新加载3D
        load3D:function(){
		    data=[];
            option= {
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {  //数据单位格式化
                        // 解析里面的数组
                        var x = params.data.value[0];
                        var y = params.data.value[1];
                        var value = params.data.value[2];

                        return "<h4 class='remind_title'>食品信息<h4><br/>" +
                            "<div class='remind_content'><li>名称：牛肉"
                            + "</li><li>数量：220斤</li><li>排号：" + x + "</li><li>列号：" + y +
                            "</li><li>保质期：<span style='color:red'>"
                            + value + "</span>天</li></div>";
                    }

                },
                visualMap: {
                    max: 20,
                    inRange: {
                        color: ['#a50026', '#fee090', '#abd9e9', '#74add1', '#4575b4', '#313695']
                    }
                },
                xAxis3D: {
                    type: 'category',
                    data: hours
                },
                yAxis3D: {
                    type: 'category',
                    data: days
                },
                zAxis3D: {
                    type: 'value'
                },
                grid3D: {
                    boxWidth: 200,
                    boxDepth: 80,
                    viewControl: {},
                    light: {
                        main: {
                            intensity: 1.2,
                            shadow: true
                        },
                        ambient: {
                            intensity: 0.3
                        }
                    }
                },
                series: [{
                    type: 'bar3D',
                    data: data.map(function (item) {
                        return {
                            value: [item[1], item[0], item[2]],
                        }
                    }),
                    shading: 'lambert',

                    label: {
                        textStyle: {
                            fontSize: 16,
                            borderWidth: 1
                        }
                    },

                    emphasis: {
                        label: {
                            textStyle: {
                                fontSize: 20,
                                color: '#900'
                            }
                        },
                        itemStyle: {
                            color: '#900'
                        }
                    }
                }]
            }
            myChart2.setOption(option);
		    console.log("成功了");
        }
	}
});

let vm1= new Vue({
    el: '#rrapp1',
    data: {
        examiner: {},
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
        reload: function (event) {
            window.location.href="warehouseBy3d.html";
        },
    }
});