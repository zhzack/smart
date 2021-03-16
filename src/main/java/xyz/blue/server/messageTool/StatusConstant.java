package xyz.blue.server.messageTool;

public interface StatusConstant {
    //    信息码开始位置
    int BEGININFOCODE = 0;
    //    信息码结束位置
    int ENDINFOCODE = 2;
    //    消息
    String MESSAGE = "00";
    //    请求
    String REQUEST = "01";
    //    回应
    String RESPOND = "02";
    //    心跳
    String CONTEST = "03";

    //身份校验位，验证用户还是设备
    int IDENTITYCODE = ENDINFOCODE;
    //    设备
    String DEVICE = "1";
    //   用户
    String USER = "0";
    int IDLENGHT = 8;
    //发送者id开始位置
    int BEGINSENTID = IDENTITYCODE + 1;
    //    发送者id结束位置
    int ENDSENTID = BEGINSENTID + IDLENGHT;
    //    接收者id开始位置
    int BEGINRECEIVEID = ENDSENTID;
    //    接收者id结束位置
    int ENDRECEIVEID = BEGINRECEIVEID + IDLENGHT;

    //    信息开始位置
    int MSGBEGIN = ENDRECEIVEID;

}
