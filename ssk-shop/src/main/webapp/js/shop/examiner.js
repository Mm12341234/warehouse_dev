$(function () {
    $("#jqGrid").Grid({
        url: '../examiner/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '姓名', name: 'name', index: 'name', width: 80},
			{label: '性别', name: 'sex', index: 'sex', width: 80,
                formatter(value,option,rowObject){
                    if(value==0){
                        return '男';
                    }else if(value==1){
                        return '女';
                    }else{
                        return '/'
                    }
                }
            },
			{label: '电话', name: 'phone', index: 'phone', width: 80},
			{label: '', name: 'provinceId', index: 'province_id', width: 80,hidden:true},
			{label: '', name: 'province', index: 'province', width: 80,hidden:true},
			{label: '', name: 'cityId', index: 'city_id', width: 80,hidden:true},
			{label: '', name: 'city', index: 'city', width: 80,hidden:true},
			{label: '', name: 'countryId', index: 'country_id', width: 80,hidden:true},
			{label: '地址', name: 'country', index: 'country', width: 80,
                formatter(value,object,rowObject){
                    return rowObject.province+rowObject.city+value;
                }
            },
			{label: '详细地址', name: 'addressDetail', index: 'address_detail', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		examiner: {proviceId:null,cityId:null,countryId:null},
        provinceList:[],
        cityList:[],
        countryList:[],
        sexList:[
            {id:0,name:"男"},
            {id:1,name:"女"}
        ],
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
            vm.examiner.provinceId='2';
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
        //省级联动
        proviceId:function(id){
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
            if(vm.examiner.provinceId!=''){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.examiner.provinceId,
                    async: true,
                    successCallback: function (r) {
                        vm.examiner.province=r.list.name;
                        console.log(vm.examiner.province);
                    }
                });
            }
        },
        //市级联动
        cityId:function(){
            if(vm.examiner.provinceId==''||vm.examiner.provinceId==null){
                vm.cityList=[];
            }else {
                // console.log(vm.dhReporter.provinceId);
                Ajax.request({
                    url: "../sysregion/infoCity/" + vm.examiner.provinceId,
                    async: true,
                    successCallback: function (r) {
                        vm.cityList=[];
                        for (var item of r.list) {
                            vm.cityList.push({value: item.id, label: item.name})
                        }
                    }
                });
            }
            // vm.countryList=[];
            vm.countryId();

            if(vm.examiner.cityId!=''&& vm.examiner.cityId!=null){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.examiner.cityId,
                    async: true,
                    successCallback: function (r) {
                        vm.examiner.city=r.list.name;
                        console.log(vm.examiner.city);
                    }
                });
            }
        },
        //区级联动
        countryId:function(){
            if(vm.examiner.cityId==''||vm.examiner.cityId==null){
                vm.countryList=[];
            }else{
                Ajax.request({
                    url: "../sysregion/infoCity/"+vm.examiner.cityId,
                    async: true,
                    successCallback: function (r) {
                        vm.countryList=[];
                        for(var item of r.list){
                            vm.countryList.push({value:item.id,label:item.name})
                        }
                    }
                });
            }
            if(vm.examiner.countryId!=''&& vm.examiner.countryId!=null){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.examiner.countryId,
                    async: true,
                    successCallback: function (r) {
                        vm.examiner.country=r.list.name;
                        console.log(vm.examiner.country);
                    }
                });
            }
        },
	}
});