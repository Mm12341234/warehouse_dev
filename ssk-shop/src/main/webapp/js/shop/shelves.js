$(function () {
    $("#jqGrid").Grid({
        url: '../shelves/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '货架名称', name: 'name', index: 'name', width: 80},
			{label: '最大容量', name: 'maxContent', index: 'max_content', width: 80},
            {label: '货架的层数', name: 'floor', index: 'floor', width: 80},
            {label: '仓库号(名字)', name: 'warehouseName', index: 'room_id', width: 80},
            {label: '房间号(名字)', name: 'roomName', index: 'room_id', width: 80},
            {label: '详细地址', name: 'addressDetail', index: 'address_detail', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		shelves: {"wareId":4},
        warehouseList:[],
        roomList:[],
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
			vm.shelves = {};
			// vm.chooseWarehouse();
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
            let url = vm.shelves.id == null ? "../shelves/save" : "../shelves/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.shelves),
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
				    url: "../shelves/delete",
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
                url: "../shelves/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.shelves = r.shelves;
                    // vm.chooseWarehouse();
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
        //仓库联动
        chooseWarehouse:function(){
            Ajax.request({
                url: "../warehouse/queryAllById/"+vm.shelves.wareId,
                async: true,
                successCallback: function (r) {
                    vm.warehouseList = [];
                    for (var item of r.list) {
                        // console.log(r.list);
                        // vm.warehouseList.push({value:item.id,label:item.name})
                        vm.warehouseList = r.list;
                    }
                    // console.log(vm.warehouseList);
                }
            });
            vm.queryRoom();
        },
        //房间联动
        queryRoom:function(){
            if(vm.shelves.wareId==''||vm.shelves.wareId==null){
                vm.roomList=[];
            }else {
                Ajax.request({
                    url: "../room/queryRoomVo/"+vm.shelves.wareId,
                    async: true,
                    contentType: "application/json",
                    successCallback: function (r) {
                        vm.roomList=r.list;
                    }
                });
            }
        },
	}
});