package cn.jhmarryme.excel.duty.controller;

import cn.jhmarryme.excel.duty.entity.CoderExcel;
import cn.jhmarryme.excel.duty.entity.vo.DutyInfoRequestVO;
import cn.jhmarryme.excel.duty.service.DutyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 1/2/21 12:28 PM
 * @modified By:
 */
@RestController
@RequestMapping("dutyInfo")
public class DutyInfoController {

    @Autowired
    private DutyInfoService dutyInfoService;

    @GetMapping("init")
    public void initDutyInfo(int year, HttpServletRequest request, HttpServletResponse response) {
        dutyInfoService.initYears(year);
    }

    @GetMapping("get")
    public void dutyInfo(int year, HttpServletRequest request, HttpServletResponse response) {
        dutyInfoService.getYearsData(year);
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象 参照{@link CoderExcel}
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("每日轮值", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        DutyInfoRequestVO requestVO =
                DutyInfoRequestVO.builder().startDate(LocalDate.of(2021, 1, 3)).endDate(LocalDate.of(2021, 12, 31)).build();
        dutyInfoService.createCoderExcelList(requestVO, response);
    }

}
