package cn.mybook.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.mybook.entity.*;
import cn.mybook.mapper.BookMapper;
import cn.mybook.mapper.BorrowMapper;
import cn.mybook.mapper.ReaderMapper;
import cn.mybook.service.IReaderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
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
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader> implements IReaderService {
    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;
    @Override
    public RespPageBean getReaderList(Integer currentPage, Integer size) {
        int distinctValue = readerMapper.getDistinctValue();
        Page<Reader> readerPage = new Page<>(currentPage,size+distinctValue);
        IPage<Reader> readerIPage = readerMapper.getReaderList(readerPage);
        readerIPage.getRecords().forEach(reader -> {
            StringBuilder bookDateBuilder = new StringBuilder();
            StringBuilder bookArrayBuilder = new StringBuilder();
            if(CollectionUtil.isNotEmpty(reader.getBookList())){
                reader.getBookList().forEach(book -> {
                    if(!StringUtils.isEmpty(book.getName())) {
                        bookArrayBuilder.append(book.getName()).append(",");
                    }
                });
                reader.setBookList(null);
                reader.setBookArray(bookArrayBuilder.deleteCharAt(bookArrayBuilder.length()-1).toString());
            }else{
                reader.setBookArray("无");
            }

            if(CollectionUtil.isNotEmpty(reader.getBorrowList())){
                reader.getBorrowList().forEach(borrow -> {
                    if(!StringUtils.isEmpty(borrow.getBookDate())){
                        bookDateBuilder.append(borrow.getBookDate()).append(",");
                    }
                });
                reader.setBorrowList(null);
                reader.setBookDate(bookDateBuilder.deleteCharAt(bookDateBuilder.length()-1).toString());
            }else{
                reader.setBookDate("无");
            }

        });
        return new RespPageBean(readerIPage.getTotal()-distinctValue,readerIPage.getRecords());
    }

    @Override
    public List<Reader> getReaderByMap(Map<String, Object> map) {
        List<Reader> bidList = readerMapper.getBidList(map);
        if(CollectionUtil.isEmpty(bidList.get(0).getBorrowList())){
            bidList.get(0).setBookDate("无").setBookArray("无");
            return bidList;
        }

        List<Reader> readerByMap = readerMapper.getReaderByMap(map);
        readerByMap.forEach(reader -> {
            StringBuilder bookDateBuilder = new StringBuilder();
            StringBuilder bookArrayBuilder = new StringBuilder();
            if(CollectionUtil.isNotEmpty(reader.getBookList())){
                reader.getBookList().forEach(book -> {
                    if(!StringUtils.isEmpty(book.getName())) {
                        bookArrayBuilder.append(book.getName()).append(",");
                    }
                });
                reader.setBookList(null);
                reader.setBookArray(bookArrayBuilder.deleteCharAt(bookArrayBuilder.length()-1).toString());
            }else{
                reader.setBookArray("无");
            }

            if(CollectionUtil.isNotEmpty(reader.getBorrowList())){
                reader.getBorrowList().forEach(borrow -> {
                    if(!StringUtils.isEmpty(borrow.getBookDate())){
                        bookDateBuilder.append(borrow.getBookDate()).append(",");
                    }
                });
                reader.setBorrowList(null);
                reader.setBookDate(bookDateBuilder.deleteCharAt(bookDateBuilder.length()-1).toString());
            }else{
                reader.setBookDate("无");
            }

        });
        return  readerByMap;
    }

    @Override
    public RespBean deleteReaderById(int id) {
        Map<String,Object> map = new HashMap<>();
        int updateNumber = 0;
        List<Borrow> deletedBorrow = borrowMapper.getDeletedBorrow(id);
        if(CollectionUtil.isNotEmpty(deletedBorrow)){
        for (Borrow borrow : deletedBorrow) {
            //改变前后差为1
            int numberBeforeUpdate = bookMapper.getNumber(borrow.getBid());
            bookMapper.updateNumber(borrow.getBid());
            int numberAfterUpdate = bookMapper.getNumber(borrow.getBid());
            if(numberAfterUpdate-numberBeforeUpdate==1){
                updateNumber++;
            }
        }
        if(updateNumber==2 || updateNumber==1){
            map.put("rid",id);
            int row = borrowMapper.deleteByMap(map);
            //一人最多借两本书，证明如果删除的记录为2行或1行则还书成功，同时各种类书籍加1
            if(row==2 || row==1){
                int deleteById = readerMapper.deleteById(id);
                if(deleteById>0){
                    return RespBean.success("删除成功");
                }
            }

        }}else{
            int deleteById = readerMapper.deleteById(id);
            if(deleteById>0){
                return RespBean.success("删除成功");
            }
        }
        return RespBean.error("删除失败");

    }

    @Override
    public int getRid(String phone) {
        return readerMapper.getRid(phone);
    }
}
