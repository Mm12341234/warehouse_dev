$(function () {
    $("#jqGrid").Grid({
        url: '../room/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '房间名称', name: 'name', index: 'name', width: 80},
			{label: '所属仓库', name: 'warehouseAddress', index: 'warehouse_id', width: 80,hidden:true},
            {label: '仓库名称', name: 'warehouseName', index: 'warehouse_id', width: 80,
                formatter(value,option,rowObject){
                    return rowObject.warehouseName+">"+rowObject.floorNum+"楼";
                }
            },
            {label: '最大容量', name: 'maxContent', index: 'max_content', width: 80},
			{label: '属于仓库的第几层楼', name: 'floorNum', index: 'floor_num', width: 80,hidden:true},
            {label: '详细地址', name: 'addressDetail', index: 'address_detail', width: 80}
        ]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		room: {},
        warehouseList:[],
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
			vm.room = {};
            vm.chooseWarehouse();
		},
		update: function (event) {
            let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.chooseWarehouse();
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            let url = vm.room.id == null ? "../room/save" : "../room/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.room),
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
				    url: "../room/delete",
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
                url: "../room/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.room = r.room;
                    // console.log(vm.room);
                    vm.room.warehouseId=r.room.warehouseId+'';
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
        chooseWarehouse:function(id){
            Ajax.request({
                url: "../warehouse/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.warehouseList=[];
                    for(var item of r.list){
                        vm.warehouseList.push({value:item.id,label:item.name})
                    }
                    console.log(vm.warehouseList);
                }
            });
        },
	}
});