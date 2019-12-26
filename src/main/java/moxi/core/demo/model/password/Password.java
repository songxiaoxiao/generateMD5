package moxi.core.demo.model.password;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author winter
 */
@TableName("password")
@Data
public class Password  extends Model<Password> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("laws")
    private String laws;

    @TableField("cipher")
    private String cipher;
        @TableField("originate")
    private String originate;
            @TableField("remark")
    private String remark;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
