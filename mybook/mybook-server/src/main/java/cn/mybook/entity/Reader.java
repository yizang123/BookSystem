package cn.mybook.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

public class Reader implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    private String name;

    private String phone;
    @TableLogic
    private Integer deleted;


    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    @TableField(exist = false)
    private List<Borrow> borrowList;
    @TableField(exist = false)
    private List<Book> bookList;
    @TableField(exist = false)
    private String bookDate;
    @TableField(exist = false)
    private String bookArray;

}
