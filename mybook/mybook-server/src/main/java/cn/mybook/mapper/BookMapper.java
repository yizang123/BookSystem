package cn.mybook.mapper;

import cn.mybook.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@Repository
public interface BookMapper extends BaseMapper<Book> {

    int updateNumber(int id);
    int getNumber(int id);
    IPage<Book> getBookList(@Param("page")Page<Book> book);
    List<Book> getBookByMap(Map<String,Object> map);
    int borrowBook(int id);
    List<Book> getBookNumberList();
}
