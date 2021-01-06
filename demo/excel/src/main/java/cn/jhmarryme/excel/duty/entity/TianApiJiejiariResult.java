package cn.jhmarryme.excel.duty.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 1/3/21 10:00 PM
 * @modified By:
 */
@Data
@ToString
public class TianApiJiejiariResult<T> {

    private String code;
    private String msg;
    private List<T> newslist;
}
