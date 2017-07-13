<#assign path=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>JoyLau-Upload</title>
    <!-- 引用控制层插件样式 -->
    <link rel="stylesheet" href="${path}/static/css/zyUpload.css" type="text/css">
    <script src="//cdn.bootcss.com/jquery/1.7.2/jquery.min.js"></script>
    <!-- 引用核心层插件 -->
    <script type="text/javascript" src="${path}/static/js/zyFile.js"></script>
    <!-- 引用控制层插件 -->
    <script type="text/javascript" src="${path}/static/js/zyUpload.js"></script>
</head>

<body>
<h1 style="text-align:center;">资源上传</h1>
<div id="up-div" class="up-div"></div>
</body>
</html>
<script>
    var path = '${path}';
    $(function(){
        // 初始化插件
        $("#up-div").zyUpload({
            width            :   "650px",                 // 宽度
            height           :   "450px",                 // 宽度
            itemWidth        :   "120px",                 // 文件项的宽度
            itemHeight       :   "100px",                 // 文件项的高度
            url              :   path + "/uploadFile",  // 上传文件的路径
            multiple         :   true,                    // 是否可以多个文件上传
            dragDrop         :   true,                    // 是否可以拖动上传文件
            del              :   true,                    // 是否可以删除文件
            finishDel        :   true  				  // 是否在上传文件完成后删除预览
            /* 外部获得的回调接口 */
            /*onSelect: function(files, allFiles){                    // 选择文件的回调方法
                console.info("当前选择了以下文件：");
                console.info(files);
                console.info("之前没上传的文件：");
                console.info(allFiles);
            },
            onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
                console.info("当前删除了此文件：");
                console.info(file);
                console.info("当前剩余的文件：");
                console.info(surplusFiles);
            },
            onSuccess: function(file,res){
                alert(res);
                var reO = JSON.parse(res);
                console.info(reO);
                // 文件上传成功的回调方法
                $("#uploadProgress_" + file.index).hide();
                $("#uploadSuccess_" + file.index).show();
                $("#uploadInf").append("<p>上传成功，文件地址是：" + reO.msg + "</p>");
                // 根据配置参数确定隐不隐藏上传成功的文件
                if(this.finishDel){
                    // 移除效果
                    $("#uploadList_" + file.index).fadeOut();
                    // 重新设置统计栏信息
                    self.funSetStatusInfo(ZYFILE.funReturnNeedFiles());
                }
            },
            onFailure: function(file){                    // 文件上传失败的回调方法
                console.info("此文件上传失败：");
                console.info(file);
            },
            onComplete: function(responseInfo){           // 上传完成的回调方法
                console.info("文件上传完成");
                console.info(responseInfo);
            }*/
        });
    });
</script>







