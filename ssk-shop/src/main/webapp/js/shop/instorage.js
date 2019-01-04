// $(function () {
//     $("#jqGrid").Grid({
//         url: '../instorage/list',
//         // multiselect:false,
//         colModel: [
//             {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
//             {label: '入库单编号', name: 'no', index: 'no', width: 80},
//             {label: '客户姓名', name: 'customerName', index: 'customer_id', width: 80},
//             {label: '类别', name: 'cateName', index: 'category_id', width: 80},
//             {label: '数量', name: 'num', index: 'num', width: 80,
//                 formatter(value){
//                     return value+"斤";
//                 }
//             },
//             {
//                 label: '货架的id', name: 'shelvesName', index: 'shelves_id', width: 140,
//                 formatter(value,option,rowObject){
//                     return rowObject.warehouseName+">"+rowObject.roomName+">"+rowObject.shelvesName;
//                 }
//             },
//             {label: '货架的层数', name: 'shelvesNum', index: 'shelves_num', width:50},
//             {label: 'boolean，0：代表自动，1：代表手动', name: 'inType', index: 'in_type', width: 80,hidden:true,
//                 formatter(value,option,rowObject){
//                     if(value==0){
//                         return '自动';
//                     }else{
//                         return '手动';
//                     }
//                 }
//             },
//             {label: '审核员', name: 'checkName', index: 'check_id', width: 80},
//             {
//                 label: '入库时间', name: 'createTime', index: 'shelves_num', width:80,
//                 formatter(value){
//                     return transDate(value);
//                 }
//             },
//             {label: '操作', name: 'status', index: 'check_id', width: 80,
//                 formatter(value,option,rowObject){
//                     if(value==0){
//                         return '<button class="ivu-btn ivu-btn-small" onclick="vm.agreeInStorage(' + rowObject.id + ')">' +
//                             '<i class="fa fa-pencil-square-o"></i>&nbsp;通过</button>'+" | "+
//                             '<button class="ivu-btn ivu-btn-small" onclick="vm.unAgreeInStorage(' + rowObject.id + ')">' +
//                             '<i class="fa fa-pencil-square-o"></i>&nbsp;不通过</button>';
//                     }else if(value==1){
//                         return "已入库";
//                     }else{
//                         return "拒绝入库";
//                     }
//                 }
//             },
//         ]
//     });
// });
$(function () {
    vm.add();
});
let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: false,
        title: null,
		inStorage: {},
        customerlist:[],
        examinerlist:[],
        catelist:[],
        warehouseList:[],
        roomList:[],
        shelvesList:[],
        suitableTemperature:null,
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
			vm.title = "手动入库";
			vm.inStorage = {};
			vm.queryCategory();
			vm.queryCustomer();
			vm.queryCheck();
			vm.chooseWarehouse();
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
            let url = vm.inStorage.id == null ? "../instorage/save" : "../instorage/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.inStorage),
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
				    url: "../instorage/delete",
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
                url: "../instorage/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.inStorage = r.inStorage;
                }
            });
		},
		reload: function (event) {
            // let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			// $("#jqGrid").jqGrid('setGridParam', {
            //     postData: {'name': vm.q.name},
            //     page: page
            // }).trigger("reloadGrid");
            // vm.handleReset('formValidate');
            vm.showList=false;
            vm.inStorage={}
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
            location.href='../autooutstorage/excelexport?sod='+str;
        },
        //查询所有客户的信息
        queryCustomer:function(){
            Ajax.request({
                url: "../customer/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.customerlist=[];
                    for(var i=0;i<r.list.length;i++){
                        vm.customerlist.push({"id":r.list[i].id,"name":r.list[i].name});
                    }
                    // console.log(vm.customerlist);
                }
            });
        },
        //查询所有的分类
        queryCategory:function(){
            Ajax.request({
                url:"../foodcategory/queryCateByLevel",
                params:JSON.stringify({"id":3}),
                type: "POST",
                contentType: "application/json",
                successCallback:function(r){
                    // console.log(r);
                    vm.catelist=[];
                    vm.catelist=r.list;
                }
            });
            //找到合适的温度
            if(vm.inStorage.categoryId!=null&&vm.inStorage.categoryId!=''){
                console.log(vm.inStorage.categoryId);
                Ajax.request({
                    url:"/suitabletemperature/queryByCateId/"+vm.inStorage.categoryId,
                    async: true,
                    successCallback: function (r) {
                        // console.log(r);
                        var temperature=r.suitableTemperature.minTemperature;
                        temperature=temperature+"-"+r.suitableTemperature.maxTemperature+"℃";
                        vm.suitableTemperature=temperature;
                        // console.log(vm.suitableTemperature);
                    }
                })
            }else{
                vm.suitableTemperature=[];
            }
        },

        //查询所有审核员的id
        queryCheck:function(){
            Ajax.request({
                url:"../examiner/queryAll",
                type: "POST",
                contentType: "application/json",
                successCallback:function(r){
                    vm.examinerlist=[];
                    for(var i=0;i<r.list.length;i++){
                        vm.examinerlist.push({"id":r.list[i].id,"name":r.list[i].name})
                    }
                    vm.examinerlist=r.list;
                }
            })
        },
        //仓库联动
        chooseWarehouse:function(){
            Ajax.request({
                url: "../warehouse/queryHouseAll",
                async: true,
                successCallback: function (r) {
                    vm.warehouseList = [];
                    // for (var item of r.list) {
                    //     vm.warehouseList = r.list;
                    // }
                    vm.warehouseList=r.list;
                }
            });
            vm.queryRoom();
        },

        //房间联动
        queryRoom:function(){
            if(vm.inStorage.warehouseId==''||vm.inStorage.warehouseId==null){
                vm.roomList=[];
                return
            }else {
                Ajax.request({
                    url: "../room/queryRoomVo/"+vm.inStorage.warehouseId,
                    async: true,
                    contentType: "application/json",
                    successCallback: function (r) {
                        vm.roomList=r.list;
                    }
                });
            }
            vm.queryShelvesList();
        },
        //货架联动
        queryShelvesList:function(){
            if(vm.inStorage.roomId==''||vm.inStorage.roomId==null){
                vm.shelvesList=[];
            }else {
                Ajax.request({
                    url: "../shelves/queryShelvesVo/"+vm.inStorage.roomId,
                    async: true,
                    successCallback: function (r) {
                        vm.shelvesList=r.list;
                        // for (var item of r.list) {
                        //     vm.shelvesList.push({value: item.id, label: item.name})
                        // }
                        // console.log(vm.shelvesList);
                    }
                });
            }
        }
	}
});