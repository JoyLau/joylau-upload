<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html>

<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- The jQuery library is a prerequisite for all jqSuite products -->
    <script type="text/ecmascript" src="//www.guriddo.net/demo/js/jquery.min.js"></script>
    <!-- This is the Javascript file of jqGrid -->
    <script type="text/ecmascript" src="//www.guriddo.net/demo/js/trirand/jquery.jqGrid.min.js"></script>
    <!-- This is the localization file of the grid controlling messages, labels, etc. -->
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="//www.guriddo.net/demo/js/trirand/i18n/grid.locale-cn.js"></script>
    <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <link rel="stylesheet" type="text/css" media="screen" href="//www.guriddo.net/demo/css/jquery-ui.css" />
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="//www.guriddo.net/demo/css/trirand/ui.jqgrid.css" />
    <title>JoyLau-Explorer</title>
    <style type="text/css">
        .ui-jqgrid .ui-jqgrid-htable .ui-th-div {
            height:22px;
        }
        .ui-jqgrid .ui-pg-selbox, .ui-jqgrid .ui-jqgrid-toppager .ui-pg-selbox {height: 22px}
    </style>
</head>
<body>
<h1 style="text-align:center;">资源浏览</h1>
<table id="jqGrid"></table>
<div id="jqGridPager"></div>

<script type="text/javascript">

    $(document).ready(function () {
        $("#jqGrid").jqGrid({
            url: 'getFileDate',
            mtype: "GET",
            datatype: "json",
            colModel: [
                { label: 'hash', name: 'hash', key: true, width: $(window).width()*0.3},
                { label: '文件名', name: 'fileName', width: $(window).width()*0.2 },
                { label: '上传时间', name: 'putTime', width: $(window).width()*0.2},
                { label: '文件类型', name: 'mimeType', width: $(window).width()*0.2},
                { name: 'putDate',width:1},
                { name: 'mimeType',hidden:true},
                {
                    label: '文件大小',
                    name: 'fileSize',
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue + "  Kb";
                    },
                    width: $(window).width()*0.2,
                    summaryTpl: "总计: {0}", // set the summary template to show the group summary
                    summaryType: "sum" // set the formula to calculate the summary type
                },
                { label:'操作', width: $(window).width()*0.1,sortable:false,
                formatter: function (cellvalue, options, rowObject) {
                    if(rowObject.mimeType.indexOf('image')>-1){
                        return '&nbsp;&nbsp;&nbsp;&nbsp;<a style="color: #00A2D4" href="//file.joylau.cn/'+rowObject.fileName+'"  target="_blank">预览</a>';
                    }
                    return '&nbsp;&nbsp;&nbsp;&nbsp;<a style="color: cadetblue" href="//file.joylau.cn/'+rowObject.fileName+'"  target="_blank">下载</a>';
                } }
            ],
            shrinkToFit:true,
            loadonce: true,
            viewrecords: true,
            width: $(window).width(),
            height: 500,
            rowNum: 20,
            rowList : [20,30,40,50],
            sortname: 'hash',
            pager: "#jqGridPager",
            footerrow: true, // set a footer row
            userDataOnFooter: true, // the calculated sums and/or strings from server are put at footer row.
            groupDataSorted : true,
            grouping: true,
            groupingView: {
                groupField: ["putDate"],
                groupColumnShow: [false],
                groupText: ["<b style='color: red'>{0} 日上传：</b>"],
                groupOrder: ["desc"],
                groupSummary: [true],
                groupCollapse: false

            }
        });
        $("#jqGrid").jqGrid("navGrid","#jqGridPager",{add:false, edit:false, del:true});
    });
</script>
</body>
</html>