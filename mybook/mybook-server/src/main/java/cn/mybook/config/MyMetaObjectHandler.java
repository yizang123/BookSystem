package cn.mybook.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//实现mybatis-plus的自动填充需要实现MetaObjectHandler
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //插入时：创建时间和修改时间
        this.setFieldValByName("gmtCreate", new Date(), metaObject); //根据值得名字填充
        this.setFieldValByName("gmtModified", new Date(), metaObject); //根据值得名字填充
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //修改时：修改时间
        this.setFieldValByName("gmtModified", new Date(), metaObject); //根据值得名字填充
    }
}
