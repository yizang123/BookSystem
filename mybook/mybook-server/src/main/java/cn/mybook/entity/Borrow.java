package cn.mybook.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    private Integer bid;


    private Integer rid;

    @TableField("book_date")
    private String bookDate;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    private Integer readied;
    @TableField(exist = false)
    private String phone;


}
