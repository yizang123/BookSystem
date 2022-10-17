package cn.mybook.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: yeb
 * @description: 分页公共返回对象
 * @author: Honors
 * @create: 2021-07-20 09:31
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {


    private Long  total;

    private List<?> data;

}