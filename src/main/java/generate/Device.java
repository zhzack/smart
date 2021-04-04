package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * device
 * @author 
 */
@Data
public class Device implements Serializable {
    private Integer deviceId;

    private String deviceName;

    private Integer userId;

    private Integer deviceMac;

    private Boolean deviceStatus;

    private static final long serialVersionUID = 1L;
}