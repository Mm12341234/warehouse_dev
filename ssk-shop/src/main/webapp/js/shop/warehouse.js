$(function () {
    $("#jqGrid").Grid({
        url: '../warehouse/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '仓库名称', name: 'name', index: 'name', width: 80},
			{label: '省份', name: 'province', index: 'province', width: 80,hidden:true},
			{label: '地区', name: 'city', index: 'city', width: 140,
				formatter(value,option,rowObject){
				    return rowObject.province+rowObject.city+rowObject.country;
				}
			},
			{label: '区域', name: 'country', index: 'country', width: 80,hidden:true},
			{label: '详细地址', name: 'addressDetail', index: 'address_detail', width: 80},
			{label: '最大容量', name: 'maxContent', index: 'max_content', width: 80},
			{label: '存储房的数量', name: 'roomNum', index: 'room_num', width: 80},
			{label: '仓库的经度', name: 'longitude', index: 'longitude', width: 80},
			{label: '仓库的纬度', name: 'dimension', index: 'dimension', width: 80},
			{label: '仓库的楼层数', name: 'floor', index: 'floor', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		warehouse: {provinceId:null,cityId:null,countryId:null,province:null,city:null,country:null},
        provinceList:[],
        cityList:[],
        countryList:[],
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
			vm.warehouse = {};
            vm.warehouse.provinceId='-1';
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
            let url = vm.warehouse.id == null ? "../warehouse/save" : "../warehouse/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.warehouse),
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
				    url: "../warehouse/delete",
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
                url: "../warehouse/info/"+id,
                async: true,
                successCallback: function (r) {
                    console.log(r);
                    vm.warehouse = r.warehouse;
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
        //省级联动
        provinceId:function(id){
            Ajax.request({
                url: "../sysregion/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.provinceList=[];
                    for(var item of r.list){
                        vm.provinceList.push({value:item.id,label:item.name})
                    }
                }
            });
            // vm.cityList=[];
            vm.cityId();
            //获取地区文字
            if(vm.warehouse.provinceId!=''){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.warehouse.provinceId,
                    async: true,
                    successCallback: function (r) {
                        vm.warehouse.province=r.list.name;
                        console.log(vm.warehouse.province);
                    }
                });
            }
        },
        //市级联动
        cityId:function(){
            if(vm.warehouse.provinceId==''||vm.warehouse.provinceId==null){
                vm.cityList=[];
            }else {
                Ajax.request({
                    url: "../sysregion/infoCity/" + vm.warehouse.provinceId,
                    async: true,
                    successCallback: function (r) {
                        vm.cityList=[];
                        for (var item of r.list) {
                            vm.cityList.push({value: item.id, label: item.name})
                        }
                    }
                });
            }
            vm.countryId();

            if(vm.warehouse.cityId!=''&& vm.warehouse.cityId!=null){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.warehouse.cityId,
                    async: true,
                    successCallback: function (r) {
                        vm.warehouse.city=r.list.name;
                        console.log(vm.warehouse.city);
                    }
                });
            }
        },
        //区级联动
        countryId:function(){
            if(vm.warehouse.cityId==''||vm.warehouse.cityId==null){
                vm.countryList=[];
            }else{
                Ajax.request({
                    url: "../sysregion/infoCity/"+vm.warehouse.cityId,
                    async: true,
                    successCallback: function (r) {
                        vm.countryList=[];
                        for(var item of r.list){
                            vm.countryList.push({value:item.id,label:item.name})
                        }
                    }
                });
            }
            if(vm.warehouse.countryId!=''&& vm.warehouse.countryId!=null){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.warehouse.countryId,
                    async: true,
                    successCallback: function (r) {
                        vm.warehouse.country=r.list.name;
                        console.log(vm.warehouse.country);
                    }
                });
            }
        },
	}
});