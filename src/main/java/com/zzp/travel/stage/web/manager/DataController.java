package com.zzp.travel.stage.web.manager;

import com.zzp.travel.stage.cms.car.service.CarService;
import com.zzp.travel.stage.cms.hotel.service.HotelService;
import com.zzp.travel.stage.cms.insurance.service.InsuranceService;
import com.zzp.travel.stage.cms.order.service.OrderService;
import com.zzp.travel.stage.cms.route.service.TravelRouteService;
import com.zzp.travel.stage.cms.scenicSpot.service.ScenicSpotService;
import com.zzp.travel.stage.cms.strategy.service.StrategyService;
import com.zzp.travel.stage.role.user.service.UserService;
import com.zzp.travel.stage.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 数据处理
 * <p>
 *  //TODO
 *  DataController.java
 * </p>
 * @version v1.0.0
 * @author 佐斯特勒
 * @date 2020/7/22 17:43
 * @see  DataController
 **/
@Controller
@RequestMapping("/manager")
public class DataController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private TravelRouteService travelRouteService;
    @Resource
    private ScenicSpotService scenicSpotService;
    @Resource
    private HotelService hotelService;
    @Resource
    private OrderService orderService;
    @Resource
    private StrategyService strategyService;
    @Resource
    private CarService carService;
    @Resource
    private InsuranceService insuranceService;


    @RequestMapping("/userData")
    public ModelAndView userDate()throws Exception{
        ModelAndView mv=BaseController.getModelAndView();
        mv.addObject("state1",userService.state1count());
        mv.addObject("state2",userService.state2count());
        mv.setViewName("data/userData");
        return mv;
    }


    @RequestMapping("/travelRouteData")
    public ModelAndView travelRouteDate()throws Exception{
        var mv=BaseController.getModelAndView();
        mv.addObject("state0",travelRouteService.state0count());
        mv.addObject("state1",travelRouteService.state1count());
        mv.addObject("state2",travelRouteService.state2count());
        mv.setViewName("data/travelRouteData");
        return mv;
    }



    @RequestMapping("/scenicSpotData")
    public ModelAndView scenicSpotDate()throws Exception{
        var mv=BaseController.getModelAndView();
        mv.addObject("state0",scenicSpotService.state0count());
        mv.addObject("state1",scenicSpotService.state1count());
        mv.addObject("state2",scenicSpotService.state2count());
        mv.setViewName("data/scenicSpotData");
        return mv;
    }

    @RequestMapping("/hotelData")
    public ModelAndView hotelDate()throws Exception{
        var mv=BaseController.getModelAndView();
        mv.addObject("state0",hotelService.state0count());
        mv.addObject("state1",hotelService.state1count());
        mv.addObject("state2",hotelService.state2count());
        mv.setViewName("data/hotelData");
        return mv;
    }


    @RequestMapping("/orderData")
    public ModelAndView orderDate()throws Exception{
        var mv=BaseController.getModelAndView();
        mv.addObject("state0",orderService.state0count());
        mv.addObject("state1",orderService.state1count());
        mv.addObject("state2",orderService.state2count());
        mv.setViewName("data/orderData");
        return mv;
    }

    @RequestMapping("/strategyData")
    public ModelAndView strategyDate()throws Exception{
        var mv=BaseController.getModelAndView();
        mv.addObject("state0",strategyService.state0count());
        mv.addObject("state1",strategyService.state1count());
        mv.addObject("state2",strategyService.state2count());
        mv.setViewName("data/strategyData");
        return mv;
    }
    @RequestMapping("/carData")
    public ModelAndView carDate()throws Exception{
        var mv=BaseController.getModelAndView();
        mv.addObject("state0",carService.state0count());
        mv.addObject("state1",carService.state1count());
        mv.addObject("state2",carService.state2count());
        mv.addObject("type0",carService.type0count());
        mv.addObject("type1",carService.type1count());
        mv.addObject("type2",carService.type2count());
        mv.setViewName("data/carData");
        return mv;
    }

    @RequestMapping("/insuranceData")
    public ModelAndView insuranceDate()throws Exception{
        var mv=BaseController.getModelAndView();
        mv.addObject("state0",insuranceService.state0count());
        mv.addObject("state1",insuranceService.state1count());
        mv.addObject("state2",insuranceService.state2count());
        mv.addObject("company0",insuranceService.company0count());
        mv.addObject("company1",insuranceService.company1count());

        mv.setViewName("data/insuranceData");
        return mv;
    }
}

