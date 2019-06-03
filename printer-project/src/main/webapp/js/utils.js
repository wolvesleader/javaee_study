jQuery.Utils ={
    //元素绑定点击事件
    bindEvent:function(target,eve,fn){
        target.each(function (i,ele) {
            $(ele).bind(eve,function(){
                fn(ele);
            });
        });
    },
    getFileUrlOnBrowser:function (file) {
        //建立一個可存取到該file的url
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
}