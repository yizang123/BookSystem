package cn.mybook.service.impl;

import cn.mybook.entity.Book;
import cn.mybook.entity.RespPageBean;
import cn.mybook.mapper.BookMapper;
import cn.mybook.service.IBookService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public RespPageBean getBookList(int currentPage, int size) {
        Page<Book> page = new Page<>(currentPage,size);
        IPage<Book> bookIPage = bookMapper.getBookList(page);
        return new RespPageBean(bookIPage.getTotal(),bookIPage.getRecords());
    }

    @Override
    public List<Book> getBookByMap(Map<String, Object> map) {
        return bookMapper.getBookByMap(map);
    }

    @Override
    public int borrowBook(int id) {
        return bookMapper.borrowBook(id);
    }

    @Override
    public int updateNumber(int id) {
        return bookMapper.updateNumber(id);
    }
}
