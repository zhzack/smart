package xyz.blue.pojo;

import lombok.Data;
import xyz.blue.server.messageTool.StatusConstant;

import java.io.Serializable;


@Data
public class Msg implements Serializable, StatusConstant {
    private static final long serialVersionUID = 1L;
    private Integer msg_id;
    private String msg_infoCode;
    private String msg_identityCode;
    private String msg_sent_id;
    private String msg_receive_id;
    private String msg_text;

    public Msg(String msg_infoCode, String msg_identityCode, String msg_sent_id, String msg_receive_id, String msg_text) {
        this.msg_infoCode = msg_infoCode;
        this.msg_identityCode = msg_identityCode;
        this.msg_sent_id = msg_sent_id;
        this.msg_receive_id = msg_receive_id;
        this.msg_text = msg_text;
    }

    public Msg(String message) {
        this.msg_infoCode = message.substring(BEGININFOCODE, ENDINFOCODE);
        this.msg_identityCode = String.valueOf(message.charAt(IDENTITYCODE));
        if (msg_identityCode.equals(DEVICE)) {
            this.msg_sent_id = message.substring(BEGINSENTID, ENDSENTID);
            for (int i = 0; i < ENDRECEIVEID; i++) {
                if (!String.valueOf(message.charAt(i + BEGINRECEIVEID)).equals("0")) {
                    int beginReceiveID = i + BEGINRECEIVEID;
                    this.msg_receive_id = message.substring(beginReceiveID, ENDRECEIVEID);
                    break;
                }
            }
        } else if (msg_identityCode.equals(USER)) {
            for (int i = 0; i < ENDSENTID; i++) {
                if (!String.valueOf(message.charAt(i + BEGINSENTID)).equals("0")) {
                    int beginSentID = i + BEGINSENTID;
                    this.msg_sent_id = message.substring(beginSentID, ENDSENTID);
                    break;
                }
            }
            this.msg_receive_id = message.substring(BEGINRECEIVEID, ENDRECEIVEID);
        }


        this.msg_text = message.substring(MSGBEGIN);

    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Msg other = (Msg) that;
        return (this.getMsg_id() == null ? other.getMsg_id() == null : this.getMsg_id().equals(other.getMsg_id()))
                && (this.getMsg_infoCode() == null ? other.getMsg_infoCode() == null : this.getMsg_infoCode().equals(other.getMsg_infoCode()))
                && (this.getMsg_identityCode() == null ? other.getMsg_identityCode() == null : this.getMsg_identityCode().equals(other.getMsg_identityCode()))
                && (this.getMsg_sent_id() == null ? other.getMsg_sent_id() == null : this.getMsg_sent_id().equals(other.getMsg_sent_id()))
                && (this.getMsg_receive_id() == null ? other.getMsg_receive_id() == null : this.getMsg_receive_id().equals(other.getMsg_receive_id()))
                && (this.getMsg_text() == null ? other.getMsg_text() == null : this.getMsg_text().equals(other.getMsg_text()));
    }

    @Override

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMsg_id() == null) ? 0 : getMsg_id().hashCode());
        result = prime * result + ((getMsg_infoCode() == null) ? 0 : getMsg_infoCode().hashCode());
        result = prime * result + ((getMsg_identityCode() == null) ? 0 : getMsg_identityCode().hashCode());
        result = prime * result + ((getMsg_sent_id() == null) ? 0 : getMsg_sent_id().hashCode());
        result = prime * result + ((getMsg_receive_id() == null) ? 0 : getMsg_receive_id().hashCode());
        result = prime * result + ((getMsg_text() == null) ? 0 : getMsg_text().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", msg_id=" + msg_id +
                ", msg_infoCode=" + msg_infoCode +
                ", msg_identityCode=" + msg_identityCode +
                ", msg_sent_id=" + msg_sent_id +
                ", msg_receive_id=" + msg_receive_id +
                ", msg_text=" + msg_text +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}