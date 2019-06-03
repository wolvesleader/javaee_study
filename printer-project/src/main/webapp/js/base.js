;(function ($) {

    jQuery.extend({
        "getBase" : function (param) {
           return jQuery.ajax({
               url: param.url,
               type: "GET",
               dataType: "json",
               async:false,
               data: param.requestParam
           });
        },
        "getBasePost" : function (param) {
            return jQuery.ajax({
                url: param.url,
                type: "POST",
                dataType: "json",
                async:false,
                data: param.requestParam
            });
        },
        "customMap":function () {

            var obj = {};
            this.put = function(key, value) {
                obj[key] = value;//把键值绑定到obj对象上
            },
            //size方法，获取Map容器的个数
            this.size = function() {
                var count = 0;
                for(var attr in obj) {
                    count++;
                }
                return count;
            },
            //get方法，根据key获取value的值
            this.get = function(key) {
                if(obj[key] || obj[key] === 0 || obj[key] === false) {
                    return obj[key]
                } else {
                    return null;
                }
            },
            //remove方法,删除方法
            this.remove = function(key) {
                if(obj[key] || obj[key] === 0 || obj[key] === false) {
                    delete obj[key]
                }
            },
            //each方法,遍历方法
            this.eachMap = function(callBack) {
                for(var attr in obj) {
                    callBack(attr, obj[attr])
                }
            };
            return this;
        },
        "customMapToString":function (customMap) {
            var customMapToString = {};
            customMap.eachMap(function (key,value) {
                //var strKey = '"'+key+'"';
                customMapToString[key] = value;
            });
            return customMapToString;
        }

    });
    



//将form里面的内容序列化成json数据
    $.fn.serializeJson=function(otherString){
        var serializeObj={},
            array=this.serializeArray();
        $(array).each(function(){
            if(serializeObj[this.name]){
                serializeObj[this.name]+=';'+this.value;
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        if(otherString!=undefined){
            var otherArray = otherString.split(';');
            $(otherArray).each(function(){
                var otherSplitArray = this.split(':');
                serializeObj[otherSplitArray[0]]=otherSplitArray[1];
            });
        }
        return serializeObj;
    };

//将josn对象赋值给form--》即数据回显
    $.fn.setForm = function(jsonValue){
        var obj = this;
        $.each(jsonValue,function(name,ival){
            var $oinput = obj.find("input[name="+name+"]");
            if($oinput.attr("type")=="checkbox"){
                if(ival !== null){
                    var checkboxObj = $("[name="+name+"]");
                    var checkArray = ival.split(";");
                    for(var i=0;i<checkboxObj.length;i++){
                        for(var j=0;j<checkArray.length;j++){
                            if(checkboxObj[i].value == checkArray[j]){
                                checkboxObj[i].click();
                            }
                        }
                    }
                }
            }
            else if($oinput.attr("type")=="radio"){
                $oinput.each(function(){
                    var radioObj = $("[name="+name+"]");
                    for(var i=0;i<radioObj.length;i++){
                        if(radioObj[i].value == ival){
                            radioObj[i].click();
                        }
                    }
                });
            }
            else if($oinput.attr("type")=="textarea"){
                obj.find("[name="+name+"]").html(ival);
            }
            else{
                obj.find("[name="+name+"]").val(ival);
            }
        })
    };

})(jQuery);