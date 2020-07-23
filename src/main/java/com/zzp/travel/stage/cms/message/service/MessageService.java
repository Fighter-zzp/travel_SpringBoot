package com.zzp.travel.stage.cms.message.service;

import com.zzp.travel.stage.cms.message.domain.Message;
import com.zzp.travel.stage.web.base.PageParam;

import java.util.List;

/**
 * 留言服务
 * <p>
 * //TODO
 * MessageService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/22 15:22
 * @see MessageService
 **/
public interface MessageService {
     /**
      * 统计
      * @return
      */
    long count();

     /**
      * 查询用户的留言数
      * @param userId
      * @return
      */
    long countByUserId(String userId);

     /**
      * 通过id查询留言
      * @param id
      * @return
      */
    Message findById(String id);

     /**
      * 查询
      * @return
      */
    List<Message> findList();

     /**
      * 保存
      * @param article
      */
    void save(Message article);

     /**
      * 更新
      * @param article
      */
    void update(Message article);

     /**
      * s删除
      * @param id
      */
    void deleteByid(String id);

     /**
      * 分页查询
      * @param currentPage
      * @param pageSize
      * @param query
      * @return
      * @throws Exception
      */
    List<Message> findByPage(int currentPage, int pageSize, String query);

     /**
      * 分页查询用户留言
      * @param currentPage
      * @param pageSize
      * @param userId
      * @return
      */
    PageParam<Message> findByPageByUserId(int currentPage, int pageSize, String userId);

}
