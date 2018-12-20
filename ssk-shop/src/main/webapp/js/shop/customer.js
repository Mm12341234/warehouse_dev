$(function () {
    $("#jqGrid").Grid({
        url: '../customer/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '客户姓名', name: 'name', index: 'name', width: 80},
			{label: '头像', name: 'headurl', index: 'headUrl', width: 80,hidden:true},
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
			{label: '详细地址', name: 'addressDetail', index: 'address_detail', width: 80},
			{label: '身份证号', name: 'idcard', index: 'IDCard', width: 80},
			{label: '手持证件照', name: 'idcardfaceurl', index: 'IDCardFaceUrl', width: 80,
                formatter: function (value) {
                     var img= '<img width="50px" height="50px"  onclick="vm.lookimg('
                         + "'"
                        + value
                        + "'"
                        + ') " src="'
                        + value
                        + '">';
                     return img;
                }}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        provinceList:[],
        cityList:[],
        countyList:[],
        sexList:[
            {id:0,name:"男"},
            {id:1,name:"女"}
        ],
		customer: {proviceId:null,cityId:null,countyId:null,province:null,city:null,county:null},
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
			vm.customer = {};
            vm.customer.provinceId='2';
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

            let url = vm.customer.id == null ? "../customer/save" : "../customer/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.customer),
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
				    url: "../customer/delete",
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
                url: "../customer/info/"+id,
                async: true,
                successCallback: function (r) {
                    console.log(r);
                    vm.customer = r.customer;
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
            if(vm.customer.provinceId!=''){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.customer.provinceId,
                    async: true,
                    successCallback: function (r) {
                        vm.customer.province=r.list.name;
                        console.log(vm.customer.provinceName);
                    }
                });
            }
        },
        //市级联动
        cityId:function(){
            if(vm.customer.provinceId==''||vm.customer.provinceId==null){
                vm.cityList=[];
            }else {
                // console.log(vm.dhReporter.provinceId);
                Ajax.request({
                    url: "../sysregion/infoCity/" + vm.customer.provinceId,
                    async: true,
                    successCallback: function (r) {
                        vm.cityList=[];
                        for (var item of r.list) {
                            vm.cityList.push({value: item.id, label: item.name})
                        }
                    }
                });
            }
            // vm.countyList=[];
            vm.countyId();

            if(vm.customer.cityId!=''&& vm.customer.cityId!=null){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.customer.cityId,
                    async: true,
                    successCallback: function (r) {
                        vm.customer.city=r.list.name;
                        console.log(vm.customer.city);
                    }
                });
            }
        },
        //区级联动
        countyId:function(){
            if(vm.customer.cityId==''||vm.customer.cityId==null){
                vm.countyList=[];
            }else{
                Ajax.request({
                    url: "../sysregion/infoCity/"+vm.customer.cityId,
                    async: true,
                    successCallback: function (r) {
                        vm.countyList=[];
                        for(var item of r.list){
                            vm.countyList.push({value:item.id,label:item.name})
                        }
                    }
                });
            }
            if(vm.customer.countryId!=''&& vm.customer.countryId!=null){
                Ajax.request({
                    url: "../sysregion/objectInfo/"+vm.customer.countryId,
                    async: true,
                    successCallback: function (r) {
                        vm.customer.country=r.list.name;
                        console.log(vm.customer.country);
                    }
                });
            }
        },
        eyeImagePicUrl: function () {
            var url = vm.customer.idcardfaceurl;
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
            vm.customer.idcardfaceurl= file.response.url;
        },
        lookimg: function (url) {
            eyeImage(url);
        }
	}
});