<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/3 0003
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>购物系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css" charset="utf-8">
    <script src="layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body style="margin: 0;padding: 0">

<div id="header" style="background: #393D49;height: 10%">
    <h1 id="shop" style="color: white ;position: absolute;top: 15px;left: 20px;">购物系统</h1>
</div>
<div id="choose" style="background: #2F4056;width: 150px;height:90% ;float: left">
    <ul id="lead" >
        <li id="show"  onclick=showList()><a style="color:white;">浏览商品</a></li>
        <li   id="search  " onclick=search()><a style="color:white;">搜索商品</a></li>
        <li  id="showorder" onclick=showorder()><a style="color:white;">购物记录</a></li>
        <li  id="exit" onclick=exitSystem()><a style="color:white;">退出系统</a></li>
    </ul>
</div>
<div style="height: 90%">
    <table id="datatable" lay-filter="datatable">

    </table>
</div>
</body>
</html>

<style>
    #lead{
        list-style-type: none;
        font-family: "Microsoft YaHei UI";
        width: 100%;
        padding: 0;
        text-align: center;
    }
    #lead li{
        width: 100%;
        height: 100px;
        font-size: 25px;
        text-align: center;
        transition: 0.5s;
        line-height: 30px;
    }
    #lead li:hover{
        cursor: pointer;
        background: #cafffd;
        transition: 1s;
    }
    #lead li a{
        position: relative;
        top: 30px;
    }
</style>
<script type="text/html" id="barDemo">
    <a class="layui-btn  layui-btn-xs " lay-event="detail">详情</a>
    <a class="layui-btn  layui-btn-xs" lay-event="buy">购买</a>
</script>
<script type="text/html" id="barDemo1">
    <a class="layui-btn  layui-btn-xs" lay-event="detail">详情</a>
    <a class="layui-btn  layui-btn-xs" lay-event="buy">购买</a>
</script>
<script>
    $(document).ready(function(){
        showList();
    });
    var searchInfo;
    function showList(){
 layui.use("table", function () {
     var table = layui.table;
     table.render({
         elem: '#datatable'
         // ,height: 312
         ,url: '/showList' //数据接口
         // ,page: true //开启分页
         ,cols: [[ //表头
             {field: 'id', title: 'ID', width:225, sort: true, fixed: 'left'}
             ,{field: 'name', title: '商品名称',width:225}
             ,{field: 'type', title: '类型',width:225}
             ,{field: 'price', title: '单价', width:225, sort:true}
             ,{field: 'num', title: '库存', width: 225, sort: true}
             ,{fixed: 'right', title:'操作', toolbar: '#barDemo',align:'center', width:250}
         ]]
     });

     table.on('tool(datatable)',function (obj) {
         var data = obj.data;
         if(obj.event=='detail'){
             $.ajax({
                 url:'/showProduct',
                 type:'post',
                 data:{'id':data.id},
                 dataType:'text',
                 success:function (infos) {
                     layer.open({
                         type:1,
                         title:'商品详情'
                         ,area: '300px;'
                         ,shade: 0.8,
                         shadeClose: true,
                         content:infos
                         });
                 },error:function () {
                    layer.msg("炸了");
                 }
                 });

         }
         else if(obj.event=='buy'){
             layer.prompt({title:'请输入购买数量'},function (val,index) {
                 layer.close(index);
                 if(data.num<val) layer.msg('库存不足');
                 else {
                        $.ajax({
                            url:'/buyById',
                            type:'post',
                            data:{'id':data.id,'num':val},
                            datatype:'text',
                            success:function (data) {
                                layer.msg('购买成功'+data);
                                window.location.reload();
                            },error:function () {
                                layer.msg('购买成功');
                                showList();
                            }
                        })
                 }
             })
         }

     });

 })
    }

    function search() {
        layer.prompt({title:'请输入关键词'},function(val, index){
            layer.close(index);
            searchInfo=val;
            oksearch();
        });
    }

    function oksearch() {
        layui.use("table", function () {
            var table = layui.table;
            table.render({
                elem: '#datatable'
                // ,height: 312
                ,url: '/search' //数据接口
                ,where:{'searchInfo':searchInfo}
                ,type:'post'
                // ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'id', title: 'ID', width:225, sort: true, fixed: 'left'}
                    ,{field: 'name', title: '商品名称',width:225}
                    ,{field: 'type', title: '类型',width:225}
                    ,{field: 'price', title: '单价', width:225, sort:true}
                    ,{field: 'num', title: '库存', width: 225, sort: true}
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo1',align:'center', width:250}
                ]]
            });
            table.on('tool(datatable)',function (obj) {
                var data = obj.data;
                if(obj.event=='detail'){
                    $.ajax({
                        url:'/showProduct',
                        type:'post',
                        data:{'id':data.id},
                        dataType:'html',
                        success:function (infos) {
                            layer.open({
                                type:1,
                                title:'商品详情'
                                ,area: '300px;'
                                ,shade: 0.8,
                                shadeClose: true,
                                content:infos
                            });
                        },error:function () {
                            layer.msg("炸了");
                        }
                    });

                }
                else if(obj.event=='buy'){
                    layer.prompt({title:'请输入购买数量'},function (val,index) {
                        layer.close(index);
                        if(data.num<val) layer.msg('库存不足');
                        else {
                            $.ajax({
                                url:'/buyById',
                                type:'post',
                                data:{'id':data.id,'num':val},
                                datatype:'text',
                                success:function (data) {
                                    layer.msg('购买成功'+data);
                                    window.location.reload();
                                },error:function () {
                                    layer.msg('购买成功');
                                    oksearch();
                                }
                            })
                        }
                    })
                }

            });
        });
    }
    function exitSystem() {
        window.location.href="/login"
    }
    function showorder() {
        layui.use("table", function () {
            var table = layui.table;
            table.render({
                elem: '#datatable'
                // ,height: 312
                ,url: '/showOrder' //数据接口
                ,type:'post'
                // ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'id', title: 'ID', width:225, sort: true, fixed: 'left'}
                    ,{field: 'name', title: '商品名称',width:225}
                    ,{field: 'type', title: '类型',width:225}
                    ,{field: 'price', title: '单价', width:225, sort:true}
                    ,{field: 'num', title: '数量', width: 225, sort: true}
                ]]
            });

        });
    }
</script>