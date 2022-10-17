package cn.mybook.mapper;

import cn.mybook.entity.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface BorrowMapper extends BaseMapper<Borrow> {
    List<Borrow> getDeletedBorrow(int id);
    int getBookCount(int id);
    int deleteBorrowByMap(Map<String,Object> map);
    int updateRentBook(Borrow borrow);
    int deleteByBid(int id);
}
