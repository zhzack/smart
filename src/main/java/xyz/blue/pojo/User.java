package xyz.blue.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 *
 * @author
 */
@Data
public class User implements Serializable {
    private Integer user_id;

    private String user_name;

    private String user_pwd;

    private static final long serialVersionUID = 1L;

    public User(String user_name, String user_pwd) {
        this.user_name = user_name;
        this.user_pwd = user_pwd;
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
        User other = (User) that;
        return (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
                && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
                && (this.getUser_pwd() == null ? other.getUser_pwd() == null : this.getUser_pwd().equals(other.getUser_pwd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getUser_pwd() == null) ? 0 : getUser_pwd().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", user_id=").append(user_id);
        sb.append(", user_name=").append(user_name);
        sb.append(", user_pwd=").append(user_pwd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}