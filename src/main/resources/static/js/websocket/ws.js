
    layui.use('form', function () {
        const form = layui.form;
    });

function sendMsg() {
    let user = [];
    $("input[name='check']:checked").each(function (i) {//把所有被选中的复选框的值存入数组
        user = user + $(this).attr("title") + ","
    });
    console.log(user)

    if (user.length > 0) {
        user = user.substr(0, user.length - 1);
    } else {
        console.log("未选中发送人")
        var content = $("#content").html();
        $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
            '                        <p><q style="color: red">' + '系统提示:请在多选框中选择要发送的用户' + '</span></p>\n' +
            '                    </div>\n' +
            '                    <br/>');
        return
    }

    var msg = $("#msg").val();
    if (msg != null) {
        $.ajax({
            method: 'get',
            url: '/sendmsg',
            data: {
                "client_id": user,
                "msg": msg
            },
            success: function (data) {
                var content = $("#content").html();
                $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
                    '                        <p><q style="color: #eb7350">' + '服务器推送  ' + msg + ' -->' + user + '</span></p>\n' +
                    '                    </div>\n' +
                    '                    <br/>');
                console.log(data);
            }
        })
    } else {
        alert("请填写要发送的用户昵称或者发送内容");
    }
}

function addDeviceBTN() {
    const divA = document.getElementById("page");
    divA.innerHTML = divA.innerHTML + '<div class="layui-form-item" id="addDevice">\n' +
        '                    <div class="layui-input-inline" style="width: 350px">\n' +
        '                        <input id="DeviceName" type="text" name="device" required lay-verify="required" placeholder="设备名称"\n' +
        '                               autocomplete="off" class="layui-input">\n' +
        '\n' +
        '                        <input id="DeviceId" type="text" name="device" required lay-verify="required" placeholder="设备id"\n' +
        '                               autocomplete="off" class="layui-input">' +
        '<button class="layui-btn" onclick="addDevice()">添加设备</button>\n' +
        '                    </div>\n' +
        '                </div>';

}

function addDevice() {

    let DeviceName = $("#DeviceName").val();
    let DeviceMac = $("#DeviceId").val();

    if (DeviceMac == null || DeviceName == null) {
        alert("您的输入有误，请检查后重新输入");
    } else {
        $.ajax({
                method: 'get',
                url: '/addDevice',
                data: {
                    DeviceName: DeviceName,
                    DeviceMac: DeviceMac
                },
                success: function (data) {
                    if (data === "success") {
                        let obj = document.getElementById("addDevice");
                        obj.innerHTML = "";
                        alert("添加成功" + data);
                        location.reload();
                    } else {
                        alert("添加失败，请检查设备是否已经绑定" + data);
                    }
                }
                , error: function () {
                    alert("连接失败");
                }
            }
        )
    }


}

function sendAll() {
    let msg = $("#msg").val();
    if (msg != null) {
        $.ajax({
            method: 'get',
            url: '/sendAll',
            data: {
                msg: msg
            },
            success: function (data) {
                var content = $("#content").html();
                $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
                    '                        <p><q style="color: #eb7350">' + '服务器推送  ' + msg + ' --> 所有用户' + '</span></p>\n' +
                    '                    </div>\n' +
                    '                    <br/>');
                console.log(data);
            }
        })
    } else {
        alert("请填写要发送的内容");
    }
}

function connect() {
    const user_name = [[${session.loginUser.user_id}]];
    const Socket = "MozWebSocket" in window ? MozWebSocket : WebSocket;
    let ws = new Socket("ws://127.0.0.1/socketServer/" + user_name);

    // if ('WebSocket' in window) {
    //     //ws = new WebSocket("ws://www.isuyu.cn:8086/socketServer/niezhiliang9595");
    //     ws = new WebSocket("ws://127.0.0.1/socketServer/niezhiliang9595");
    // } else if ('MozWebSocket' in window) {
    //     //ws = new MozWebSocket("ws://www.isuyu.cn:8086/socketServer/niezhiliang9595");
    //     ws = new MozWebSocket("ws://127.0.0.1/socketServer/niezhiliang9595");
    // } else {
    //     alert("该浏览器不支持websocket");
    // }
    ws.onmessage = function (evt) {
        var content = $("#content").html();
        $("#content").html(content + '<div style="text-align: right;margin-bottom: 8px">\n' +
            '                        <p><q style="color: mediumorchid;text-align: right">' + evt.data + '</span></p>\n' +
            '                    </div>\n' +
            '                    <br/>');
        console.log(msg)
    };

    ws.onclose = function (evt) {
        console.log('连接关闭')
    };

    ws.onopen = function (evt) {
        var content = $("#content").html();
        $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
            '                        <p><q style="color: #eb7350">' + '服务器初始化成功...' + '</span></p>\n' +
            '                    </div>\n' +
            '                    <br/>');
        console.log('连接成功')
    };
}

connect()
