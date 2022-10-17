package cn.mybook.service;

import cn.mybook.entity.Book;
import cn.mybook.entity.RespPageBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
public interface IBookService extends IService<Book> {
    RespPageBean getBookList(int currentPage, int size);
    List<Book> getBookByMap(Map<String,Object> map);
    int borrowBook(int id);
    int updateNumber(int id);
}
