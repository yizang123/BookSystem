package cn.mybook.api.controller;


import cn.mybook.entity.Book;
import cn.mybook.entity.RespBean;
import cn.mybook.entity.RespPageBean;
import cn.mybook.service.IBookService;
import cn.mybook.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IBorrowService borrowService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public RespPageBean getBookList(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size){
        return bookService.getBookList(currentPage,size);
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<Book> getBookByMap(@RequestParam(value = "name",required = false)String name,
                                   @RequestParam(value = "code",required = false)String code){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("code",code);
        return bookService.getBookByMap(map);
    }
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public RespBean updateBook(@RequestBody Book book){
        boolean updateById = bookService.updateById(book);
        if(updateById){
            return RespBean.success("编辑成功");
        }
        return RespBean.error("信息有误");
    }
    @RequestMapping(value = "/deleteBatch/id",method = RequestMethod.DELETE)
    public RespBean deleteBookBatch(@RequestParam("id")List<Integer> id){
        boolean flag = false;
        for (Integer bookId : id) {
            boolean removeById = bookService.removeById(bookId);
            if(removeById){
                int byBid = borrowService.deleteByBid(bookId);
                flag = byBid > 0;
            }else{
                flag = false;
            }
        }
        if(flag){
            return RespBean.success("删除图书信息成功");
        }
        return RespBean.error("任务出错");
    }


}
