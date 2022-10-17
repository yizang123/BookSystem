package cn.mybook.api.controller;


import cn.mybook.entity.*;
import cn.mybook.service.IBookService;
import cn.mybook.service.IBorrowService;
import cn.mybook.service.IReaderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private IBorrowService borrowService;
    @Autowired
    private IReaderService readerService;
    @Autowired
    private IBookService bookService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RespBean addBorrow(@RequestBody Borrow borrow){
        Reader reader = readerService.getOne(new QueryWrapper<Reader>().eq("phone", borrow.getPhone()));
        if(reader!=null){
            //借书图书总数量-1,并且只限制两次借书
            int bookCount = borrowService.getBookCount(reader.getId());
            if(bookCount>=2){
                return RespBean.error("一个人最多借2本书");
            }else if(bookCount>=0) {
                boolean save = borrowService.save(borrow.setRid(reader.getId()));
                if (save) {
                    int borrowBook = bookService.borrowBook(borrow.getBid());
                    if (borrowBook > 0) {
                        return RespBean.success("借书登记成功!");
                    }
                }

            }
        }else {
            return RespBean.error("手机号不存在");
        }
        return RespBean.error("借书登记异常!");
    }
    @RequestMapping(value ="/back",method = RequestMethod.DELETE)
    public RespBean backBook(@RequestParam("phone")String phone,@RequestParam("id")int bid) {
        int rid = readerService.getRid(phone);
        if (rid > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("bid", bid);
            map.put("rid", rid);
            int delete = borrowService.deleteBorrowByMap(map);
            if (delete > 0) {
                int updateNumber = bookService.updateNumber(bid);
                if (updateNumber > 0) {
                    return RespBean.success("还书成功!");
                }
            }
        }
        else {
            return RespBean.error("手机号不存在");
        }
        return RespBean.error("还书失败");
    }
    @RequestMapping(value = "/backRent",method = RequestMethod.POST)
    public RespBean backRent(@RequestBody Borrow borrow){
        Reader reader = readerService.getOne(new QueryWrapper<Reader>().eq("phone", borrow.getPhone()));
        if(reader!=null){
            //进行预留登记时查看是否已经借了两本书
            int bookCount = borrowService.getBookCount(reader.getId());
            if(bookCount>=2){
                return RespBean.error("借书已超额,无法预留");
            }else if(bookCount>=0){
                boolean save = borrowService.save(borrow.setRid(reader.getId()).setReadied(1));
                if(save){
                    return RespBean.success("预留登记成功,届时来取,默认期限为30天！");
                }
            }
        }else{
            return RespBean.error("该读者不存在");
        }
        return RespBean.error("预留登记异常");
    }
}
