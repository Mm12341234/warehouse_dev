$(function () {
    $("#jqGrid").Grid({
        url: '../currentfood/list',
        multiselect: false,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '交易单编号', name: 'no', index: 'no', width: 80},
            {label: '客户姓名', name: 'customerName', index: 'customer_id', width: 80},
            {label: '类别', name: 'cateName', index: 'category_id', width: 80},
            {label: '数量', name: 'num', index: 'num', width: 80},
            {
                label: '货架位置', name: 'shelvesName', index: 'shelves_id', width: 140,
                formatter(value,option,rowObject){
                    return rowObject.warehouseName+">"+rowObject.roomName+">"+rowObject.shelvesName;
                }
            },
            {label: '货架的层数', name: 'shelvesNum', index: 'shelves_num', width: 80},
            {
                label: '入库方式', name: 'inType', index: 'in_type', width: 80,
                formatter(value){
                    if(value==0){
                        return "自动";
                    }else if(value==1){
                        return "手动";
                    }else{
                        return "未设置";
                    }
                }
            },
            {label: '审核员', name: 'checkName', index: 'check_id', width: 80},
            {
                label: '',name:'操作',width:120,
                formatter(value,option,rowObject){
                    return '<button class="ivu-btn ivu-btn-small" style="margin-top: 10px;" ' +
                        'onclick="vm.check(' + rowObject.id + ')"><i class="fa fa-pencil-square-o"></i>&nbsp;出库</button>';
                }
            }]
    });
});
let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        showUserDetail:false,
        index:null,
        outStorage: {},
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
			vm.outStorage = {};
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
            // let url = vm.outStorage.id == null ? "../outstorage/save" : "../outstorage/update";
            var url="../currentfood/outStorage";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.outStorage),
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
				    url: "../outstorage/delete",
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
                url: "../currentfood/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.outStorage = r.currentFood;
                    vm.outStorage.isOutstorage=2;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            //关闭模态框
            layer.close(vm.index);

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
        check:function(id){
            vm.getInfo(id);
            vm.index=openWindow({
                title: "手动出库",
                area: [ '600px'],
                content: jQuery("#sendDiv")
            })
            console.log(vm.index);
        },
        saveCheckComment:function(){
            // console.log(vm.userDetail);
            vm.saveOrUpdate();
            // vm.showSubmit=false;
            vm.reload();
        },
	}
});