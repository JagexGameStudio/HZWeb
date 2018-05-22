layui.use(['layer', 'form'], function(){
    var layer = layui.layer
});
//一般直接写在一个js文件中
function add(pId) {
    layer.open({
        type : 2,
        title : '增加菜单',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : '/sys/menu/add/' + pId, // iframe的url
        end:function () {
            reLoad();
        }
    });
};
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : '/sys/menu/remove/'+id,
            type : "get",
            success : function(data) {
                if (data.code == 0) {
                    layer.msg("删除成功");
                    reLoad();
                } else {
                    layer.msg(data.msg);
                }
            }
        });
    })
};
function edit(id) {
    layer.open({
        type : 2,
        title : '编辑菜单',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '750px', '400px' ],
        content : '/sys/menu/edit/' + id, // iframe的url
        end:function () {
            reLoad();
        }
    });
};
function reLoad() {
    window.location.reload();
}