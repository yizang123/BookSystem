package cn.mybook.service.impl;

import cn.mybook.entity.Borrow;
import cn.mybook.mapper.BorrowMapper;
import cn.mybook.service.IBorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements IBorrowService {
    @Autowired
    private BorrowMapper borrowMapper;
    @Override
    public int getBookCount(int id) {
        return borrowMapper.getBookCount(id);
    }

    @Override
    public int deleteBorrowByMap(Map<String, Object> map) {
        return borrowMapper.deleteBorrowByMap(map);
    }

    @Override
    public int deleteByBid(int id) {
        return borrowMapper.deleteByBid(id);
    }
}
