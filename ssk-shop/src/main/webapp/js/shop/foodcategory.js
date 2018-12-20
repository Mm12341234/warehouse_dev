// $(function () {
//     $("#jqGrid").Grid({
//         url: '../foodcategory/list',
//         colModel: [
// 			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
// 			{label: '名称', name: 'name', index: 'name', width: 80},
// 			{label: '', name: 'parentId', index: 'parent_id', width: 80},
// 			{label: '等级', name: 'level', index: 'level', width: 80},
// 			{label: '备注', name: 'remark', index: 'remark', width: 80}]
//     });
// });
$(function () {
    initialPage();
    getGrid();
});

function initialPage() {
    $(window).resize(function () {
        TreeGrid.table.resetHeight({height: $(window).height() - 100});
    });
}

function getGrid() {
    var colunms = TreeGrid.initColumn();
    var table = new TreeTable(TreeGrid.id, '../foodcategory/queryAll', colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.setHeight($(window).height() - 100);
    table.init();
    TreeGrid.table = table;
}
var TreeGrid = {
    id: "jqGrid",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TreeGrid.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', align: 'id', width: '50px'},
        {title: '分类名称', field: 'name', align: 'center', valign: 'middle', width: '100px'},
        {title: '描述', field: 'remark', align: 'center', valign: 'middle', width: '150px'},
        {
            title: '显示',
            field: 'isShow',
            align: 'center',
            valign: 'middle',
            width: '50px',
            formatter: function (item, index) {
                return transIsNot(item.show)
            }
        },
        // {title: '类型', field: 'type', align: 'center', valign: 'middle', width: '50px'},
        {title: '级别', field: 'level', align: 'center', valign: 'middle', width: '50px'}]
    return columns;
};

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        showList1:false,
        showModifyTAG:false,
        title: null,
        itemId:null,
        categoryList: [],
        categoryList1:[],
		foodCategory: {},
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
			vm.foodCategory = {};
		},
		update: function (event) {
            // let id = getSelectedRow("#jqGrid");
			// if (id == null) {
			// 	return;
			// }
            var id = TreeGrid.table.getSelectedRow();
            if (id.length == 0) {
                iview.Message.error("请选择一条记录");
                return;
            }
            id=id[0].id;
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    if(vm.foodCategory.level==1){
		        vm.foodCategory.parentId=0;
            }
            let url = vm.foodCategory.id == null ? "../foodcategory/save" : "../foodcategory/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.foodCategory),
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
				    url: "../foodcategory/delete",
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
                url: "../foodcategory/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.foodCategory = r.foodCategory;
                    vm.getParentCategory();
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
            TreeGrid.table.refresh();
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
        //下载导入模板
        load:function(){
            location.href = '../foodcategory/excelexport';
        },
        //修改TAG的关键字以及描述
        handleSubmit1: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
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
        handleSuccessExcel:function(){
            vm.reload();
            alert("导入成功");
        },
        //一级节点
        getParentCategory: function () {
            Ajax.request({
                url: "../foodcategory/queryCateByLevel",
                type:"POST",
                params:JSON.stringify({"id":1}),
                contentType: "application/json",
                async: true,
                successCallback: function (r) {
                    vm.categoryList = r.list;
                }
            });
        },
        //二级节点
        getParentCategory1:function(){
            Ajax.request({
                url:"../foodcategory/queryCateByParentId",
                type:"POST",
                contentType: "application/json",
                params:JSON.stringify({"parentId":vm.itemId}),
                async:true,
                successCallback:function(r){
                    vm.categoryList1=r.list;
                }
            })
        },
	}
});