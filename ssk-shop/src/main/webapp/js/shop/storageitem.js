$(function () {
    $("#jqGrid").Grid({
        url: '../storageitem/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '交易单编号', name: 'no', index: 'no', width: 80},
			{label: '', name: 'customerId', index: 'customer_id', width: 80},
			{label: '', name: 'categoryId', index: 'category_id', width: 80},
			{label: '数量', name: 'num', index: 'num', width: 80},
			{label: '货架的id', name: 'shelvesId', index: 'shelves_id', width: 80},
			{label: '货架的层数', name: 'shelvesNum', index: 'shelves_num', width: 80},
			{label: 'boolean，0：代表自动，1：代表手动', name: 'inType', index: 'in_type', width: 80},
			{label: '审核员', name: 'checkId', index: 'check_id', width: 80},
			{label: 'int，0：代表正常，1：代表腐损', name: 'isNormal', index: 'is_normal', width: 80},
			{label: '', name: 'finishTime', index: 'finish_time', width: 80}]
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
        }
	}
});