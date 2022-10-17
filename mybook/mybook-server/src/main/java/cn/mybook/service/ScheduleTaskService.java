package cn.mybook.service;

import cn.mybook.entity.Book;
import cn.mybook.entity.Borrow;
import cn.mybook.mapper.BookMapper;
import cn.mybook.mapper.BorrowMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class ScheduleTaskService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getLogger(ScheduleTaskService.class);
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;
    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduleTask(){
        List<Book> bookNumberList = bookMapper.getBookNumberList();
        bookNumberList.forEach(book -> {
            if(book.getNumber()>0){
                try {
                    int updateRentBook = borrowMapper.updateRentBook(new Borrow().setBookDate(dateFormat.format(new Date(System.currentTimeMillis())) + "~" + dateFormat.format(addDate(new Date(System.currentTimeMillis()), 30))).setBid(book.getId()));
                    if(updateRentBook>0){
                        int borrowBook = bookMapper.borrowBook(book.getId());
                        if(borrowBook>0){
                            logger.info(dateFormat.format(new Date(System.currentTimeMillis()))+"预留借书任务执行成功!");
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static Date addDate(Date date, long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


}
