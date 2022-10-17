package cn.mybook.service;

import cn.mybook.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wqm
 * @since 2022-10-07
 */
public interface IBorrowService extends IService<Borrow> {
    int getBookCount(int id);
    int deleteBorrowByMap(Map<String,Object> map);
    int deleteByBid(int id);
}
