package cn.jhmarryme.excel.duty.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 1/2/21 12:32 PM
 * @modified By:
 */
@Data
@Builder
public class DutyInfoRequestVO {

    private LocalDate startDate;

    private LocalDate endDate;
}
