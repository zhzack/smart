// noParameter_device()
// parameter_device()
// devices()
// connect()
// // document.getElementById("device-all").
// layui.use('form', function () {
//     const form = layui.form;
// });
//
// function sand_parameter_msg() {
//
// }
//
// function noParameter_device() {
//     let noParameter_device = document.getElementById("noParameter_device");
//     noParameter_device.innerHTML = noParameter_device.innerHTML + "<div class=\"card-outline-primary\">\n" +
//         "                                            <button type=\"button\" class=\"btn btn-inverse-success btn-block btn-fw\">卧室灯\n" +
//         "                                            </button>\n" +
//         "                                        </div>\n" +
//         "                                        <div class=\"card-outline-primary border-left-0 grid-margin-xl-0\">\n" +
//         "                                            <button type=\"button\" class=\"btn btn-inverse-primary btn-block btn-fw\">客厅灯\n" +
//         "                                            </button>\n" +
//         "                                        </div>";
// }
//
// function parameter_device() {
//     let parameter_device = document.getElementById("parameter_device");
//     parameter_device.innerHTML = "<button type=\"button\" class=\"btn btn-inverse-warning btn-block btn-fw\"\n" +
//         "                                            onclick=\"parameter_msg()\">\n" +
//         "                                                客厅空调\n" +
//         "                                            </button>";
//
// }
//
// function parameter_msg() {
//     let parameter_msg = document.getElementById("parameter_msg");
//     parameter_msg.innerHTML = "<form class=\"forms-sample\">\n" +
//         "                                                <div class=\"card-title\">\n" +
//         "                                                <button type=\"button\" class=\"btn btn-block btn-light btn-large\">空调设置</button>\n" +
//         "                                                </div>\n" +
//         "                                                <div class=\"form-group row\">\n" +
//         "                                                    <label for=\"exampleInputUsername2\"\n" +
//         "                                                           class=\"col-sm-3 col-form-label\">温度</label>\n" +
//         "                                                    <div class=\"col-sm-9\">\n" +
//         "                                                        <input type=\"text\" class=\"form-control\"\n" +
//         "                                                               id=\"exampleInputUsername2\" placeholder=\"温度（16-30）\">\n" +
//         "                                                    </div>\n" +
//         "                                                </div>\n" +
//         "\n" +
//         "                                                <div class=\"form-group row\" id=\"input_data\">\n" +
//         "                                                    <label for=\"exampleFormControlSelect2\"\n" +
//         "                                                           class=\"col-sm-3 col-form-label\">模式</label>\n" +
//         "                                                    <div class=\"col-sm-9\">\n" +
//         "                                                        <select class=\" form-control\"\n" +
//         "                                                                id=\"exampleFormControlSelect2\">\n" +
//         "                                                            <option>制冷</option>\n" +
//         "                                                            <option>制暖</option>\n" +
//         "                                                            <option>换气</option>\n" +
//         "                                                        </select>\n" +
//         "                                                    </div>\n" +
//         "                                                </div>\n" +
//         "                                                <button type=\"button\" class=\"btn btn-block btn-dark\">完成设置</button>\n" +
//         "\n" +
//         "                                            </form>"
//
// }
//
// function deviceShow(deviceId) {
//     let deviceShow = document.getElementById("deviceShow");
//     deviceShow.innerHTML = "<h1>123456</h1>";
// }
//
// function devices() {
//     let onLineDevices = document.getElementById("device-online");
//     let Devices = document.getElementById("device-all");
//     let onLine_sum = document.getElementById("onLine_sum");
//     $.ajax({
//         method: 'get',
//         url: '/getDevicesByUserId',
//         dataType: "json",
//         success: function (data) {
//             Devices.innerHTML = "";
//             data.devices.forEach(function (item) {
//                 Devices.innerHTML = Devices.innerHTML + "<ul class=\"nav flex-column sub-menu\" >\n" +
//                     "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"#" + item.device_id + " \">" + item.device_name + "</a>\n" +
//                     "                    </li>\n" +
//                     "                </ul>";
//             })
//             onLineDevices.innerHTML = "";
//             data.devicesOnLine.forEach(function (item) {
//                 onLineDevices.innerHTML = onLineDevices.innerHTML + "<ul class=\"nav flex-column sub-menu\" >\n" +
//                     "                    <li class=\"nav-item\"><a class=\"nav-link\" href=\"#" + item.device_id + " \">" + "<spam class=\"menu-title\">" + item.device_name + "</spam></a>\n" +
//                     "                    </li>\n" +
//                     "                </ul>";
//             })
//             onLine_sum.innerHTML = data.devicesOnLine.length;
//             //let deviceData=JSON.parse(data);
//             setTimeout(devices, 3000);
//             // console.log(data.devices[0]);
//             // console.log(data.devicesOnLine);
//             // console.log(data);
//         }
//     })
//
// }
//
// function sendMsg() {
//     let user = [];
//     $("input[name='check']:checked").each(function (i) {//把所有被选中的复选框的值存入数组
//         user = user + $(this).attr("title") + ","
//     });
//     console.log(user)
//
//     if (user.length > 0) {
//         user = user.substr(0, user.length - 1);
//     } else {
//         console.log("未选中发送人")
//         var content = $("#content").html();
//         $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
//             '                        <p><q style="color: red">' + '系统提示:请在多选框中选择要发送的用户' + '</span></p>\n' +
//             '                    </div>\n' +
//             '                    <br/>');
//         return
//     }
//
//     var msg = $("#msg").val();
//     if (msg != null) {
//         $.ajax({
//             method: 'get',
//             url: '/sendmsg',
//             data: {
//                 "client_id": user,
//                 "msg": msg
//             },
//             success: function (data) {
//                 var content = $("#content").html();
//                 $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
//                     '                        <p><q style="color: #eb7350">' + '服务器推送  ' + msg + ' -->' + user + '</span></p>\n' +
//                     '                    </div>\n' +
//                     '                    <br/>');
//                 console.log(data);
//             }
//         })
//     } else {
//         alert("请填写要发送的用户昵称或者发送内容");
//     }
// }
//
// function addDeviceBTN() {
//     const divA = document.getElementById("page");
//     divA.innerHTML = divA.innerHTML + '<div class="layui-form-item" id="addDevice">\n' +
//         '                    <div class="layui-input-inline" style="width: 350px">\n' +
//         '                        <input id="DeviceName" type="text" name="device" required lay-verify="required" placeholder="设备名称"\n' +
//         '                               autocomplete="off" class="layui-input">\n' +
//         '\n' +
//         '                        <input id="DeviceId" type="text" name="device" required lay-verify="required" placeholder="设备id"\n' +
//         '                               autocomplete="off" class="layui-input">' +
//         '<button class="layui-btn" onclick="addDevice()">添加设备</button>\n' +
//         '                    </div>\n' +
//         '                </div>';
//
// }
//
// function addDevice() {
//
//     let DeviceName = $("#DeviceName").val();
//     let DeviceMac = $("#DeviceId").val();
//
//     if (DeviceMac == null || DeviceName == null) {
//         alert("您的输入有误，请检查后重新输入");
//     } else {
//         $.ajax({
//                 method: 'get',
//                 url: '/addDevice',
//                 data: {
//                     DeviceName: DeviceName,
//                     DeviceMac: DeviceMac
//                 },
//                 success: function (data) {
//                     if (data === "success") {
//                         let obj = document.getElementById("addDevice");
//                         obj.innerHTML = "";
//                         alert("添加成功" + data);
//                         location.reload();
//                     } else {
//                         alert("添加失败，请检查设备是否已经绑定" + data);
//                     }
//                 }
//                 , error: function () {
//                     alert("连接失败");
//                 }
//             }
//         )
//     }
//
//
// }
//
// function sendAll() {
//     let msg = $("#msg").val();
//     if (msg != null) {
//         $.ajax({
//             method: 'get',
//             url: '/sendAll',
//             data: {
//                 msg: msg
//             },
//             success: function (data) {
//                 var content = $("#content").html();
//                 $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
//                     '                        <p><q style="color: #eb7350">' + '服务器推送  ' + msg + ' --> 所有用户' + '</span></p>\n' +
//                     '                    </div>\n' +
//                     '                    <br/>');
//                 console.log(data);
//             }
//         })
//     } else {
//         alert("请填写要发送的内容");
//     }
// }
//
// function connect() {
//     const user_name = [[${session.loginUser.user_id}]];
//     const Socket = "MozWebSocket" in window ? MozWebSocket : WebSocket;
//     let ws = new Socket("ws://127.0.0.1/ws/" + user_name);
//
//     ws.onmessage = function (evt) {
//         var content = $("#content").html();
//         $("#content").html(content + '<div style="text-align: right;margin-bottom: 8px">\n' +
//             '                        <p><q style="color: mediumorchid;text-align: right">' + evt.data + '</span></p>\n' +
//             '                    </div>\n' +
//             '                    <br/>');
//         console.log(msg)
//     };
//
//     ws.onclose = function (evt) {
//         console.log('连接关闭')
//     };
//
//     ws.onopen = function (evt) {
//         var content = $("#content").html();
//         $("#content").html(content + '<div style="margin-bottom: 8px">\n' +
//             '                        <p><q style="color: #eb7350">' + '服务器初始化成功...' + '</span></p>\n' +
//             '                    </div>\n' +
//             '                    <br/>');
//         console.log('连接成功')
//     };
// }
