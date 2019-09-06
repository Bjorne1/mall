package com.wcs.mall.order.jpa;

import com.wcs.mall.order.entity.MallOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/9/5 10:42
 */
public interface JpaMallOrderDao extends JpaRepository<MallOrder, String> {

    @Query(value = "SELECT * FROM mall_order WHERE user_id=?1 ORDER BY create_time DESC LIMIT ?2 , ?3", nativeQuery = true)
    List<MallOrder> findAllByUserId(String userId, int start, int size);

    @Query(value = "SELECT * FROM mall_order WHERE user_id=?1 AND status=?2 AND del=?3 ORDER BY create_time DESC LIMIT ?4 , ?5", nativeQuery = true)
    List<MallOrder> findByUserIdAndStatusAndDel(String userId, int status, int del, int start, int size);

    @Query(value = "SELECT * FROM mall_order WHERE user_id=?1 AND del=?2 ORDER BY create_time DESC LIMIT ?3 , ?4", nativeQuery = true)
    List<MallOrder> findByUserIdAndDel(String userId, int del, int start, int size);

    @Query(value = "SELECT COUNT(*) FROM mall_order WHERE user_id=?1 AND status=?2 AND del=?3", nativeQuery = true)
    int getCountByUserIdAndStatusAndDel(String userId, int status, int del);

    @Query(value = "SELECT COUNT(*) FROM mall_order WHERE user_id=?1 AND del=?2", nativeQuery = true)
    int getCountByUserIdAndDel(String userId, int del);
}
