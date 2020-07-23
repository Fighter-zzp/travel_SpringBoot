package com.zzp.travel.stage.role.user.service;

import com.zzp.travel.stage.role.user.vo.ProvinceVO;

import java.util.List;

public interface ProvinceService {
    /**
     * 获取城市类型和计数
     * @return {@link List<ProvinceVO>}
     */
    List<ProvinceVO> getProvinceCount();
}
