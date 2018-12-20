$(function () {
    $("#jqGrid").Grid({
        url: '../autooutstorage/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '交易单编号', name: 'no', index: 'no', width: 80},
			{label: '客户', name: 'customerName', index: 'customer_id', width: 80},
			{label: '分类', name: 'cateName', index: 'category_id', width: 80},
			{label: '数量', name: 'num', index: 'num', width: 80},
			{label: '货架的id', name: 'shelvesId', index: 'shelves_id', width: 80},
			{label: '货架的层数', name: 'shelvesNum', index: 'shelves_num', width: 80},
			{label: 'boolean，0：代表自动，1：代表手动', name: 'inType', index: 'in_type', width: 80,hidden:true},
			{label: '审核员', name: 'examinerName', index: 'check_id', width: 80},
			{
				label: '出库类型', name: 'isNormal', index: 'is_normal', width: 80,
				formatter(value){
					if(value==0){
						return "正常出库";
					}else if(value==1){
						return "腐损出库"
					}else{
						return "未设置"
					}
				}
			},
			{label: '完成时间', name: '', index: 'finish_time', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		outStorage: {},
        customerlist:[],
        examinerlist:[],
        catelist:[],
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
            let url = vm.outStorage.id == null ? "../autooutstorage/save" : "../autooutstorage/update";
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
				    url: "../autooutstorage/delete",
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
                url: "../autooutstorage/info/"+id,
                async: true,
                successCallback: function (r) {
                	console.log(r);
                    vm.outStorage = r.outstorage;
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
                    console.log(vm.customerlist);
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
                    vm.catelist=[];
                    vm.catelist=r.list;
                }
            })
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
        }
	}
});