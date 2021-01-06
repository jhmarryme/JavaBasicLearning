package cn.jhmarryme.excel.duty.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 1/2/21 12:03 PM
 * @modified By:
 */
@Data
@Builder
public class CoderExcel {

    @ExcelProperty("姓名")
    private String name;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("时间")
    private Date date;

    @ExcelProperty("日期")
    private String whatDay;

    @ExcelProperty("remark")
    private String remark;
}
