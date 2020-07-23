package com.zzp.travel.stage.cms.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.travel.stage.cms.message.domain.Message;
import com.zzp.travel.stage.cms.order.domain.Order;
import com.zzp.travel.stage.cms.order.mapper.OrderMapper;
import com.zzp.travel.stage.cms.order.service.OrderService;
import com.zzp.travel.stage.common.template.CommonTemplate;
import com.zzp.travel.stage.utils.Tools;
import com.zzp.travel.stage.web.base.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MapperUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * <标题>
 * <p>
 *  //TODO
 *  OrderServiceImpl.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 17:15
 * @see  OrderServiceImpl
 **/
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CommonTemplate<Order> commonTemplate;

    @Override
    public long count() {
        return orderMapper.selectCountByExample(whereArgs("deleteStatus","0"));
    }

    @Override
    public Order findById(String id){
        return orderMapper.selectOneByExample(whereArgs("deleteStatus","0","id",id));
    }

    @Override
    public List<Order> findList() {
        var example = whereArgs("deleteStatus", "0");
        example.orderBy("addTime").desc();
        return orderMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Order article) {
        article.setAddTime(new Date());
        orderMapper.insertSelective(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Order article) {
        article.setModifyTime(new Date());
        var id = article.getId();
        article.setId(null);
        orderMapper.updateByExampleSelective(article,whereArgs("deleteStatus", "0","id",id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        var order = new Order();
        order.setId(id);
        order.setDeleteStatus(0);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<Order> findByPage(int currentPage, int pageSize, String query){
        List<Order> list;
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = orderMapper.findByQuery("%" + query + "%");
        } else {
            list = findList();
        }
        var pageInfo=new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Order> findListByUserId(String userId){
        return orderMapper.findListByUserId(userId);
    }

    @Override
    public long countByUserId(String userId) {
        return orderMapper.countByUserId(userId);
    }

    @Override
    public PageParam<Order> findByPageByUserId(int currentPage, int pageSize, String userId){
        return commonTemplate.findByPageByUserId(currentPage,
                pageSize,
                userId,
                ((c, p, uid) -> orderMapper.findListByUserId(uid)));
    }

    @Override
    public long state0count() throws Exception {
        return orderMapper.state0count();
    }

    @Override
    public long state1count() throws Exception {
        return orderMapper.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return orderMapper.state2count();
    }

    /**
     * where值设置
     * @param args 参数
     * @return Example
     *
     */
    private Example whereArgs(String ...args){
        return MapperUtils.whereArgs(Order.class, args);
    }

}
