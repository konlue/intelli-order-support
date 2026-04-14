package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 根据时间区间统计营业额
     */
    public TurnoverReportVO getTurnover(LocalDate beginTime, LocalDate endTime) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(beginTime);

        while (!beginTime.equals(endTime)){
            beginTime = beginTime.plusDays(1);//日期计算，获得指定日期后1天的日期
            dateList.add(beginTime);
        }

        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : dateList) {
            LocalDateTime begin = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
//            Map map = new HashMap();
//            map.put("status", Orders.COMPLETED);
//            map.put("begin",beginTime);
//            map.put("end", endTime);
            Integer status = Orders.COMPLETED;

            Double turnover = orderMapper.sumByMap(status, begin, end);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }

        //数据封装
        return TurnoverReportVO.builder()
                .dateList(StringUtils.join(dateList,","))
                .turnoverList(StringUtils.join(turnoverList,","))
                .build();
    }
}