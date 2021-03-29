package com.jhmarryme.service;

import com.jhmarryme.pojo.Items;
import com.jhmarryme.pojo.ItemsImg;
import com.jhmarryme.pojo.ItemsParam;
import com.jhmarryme.pojo.ItemsSpec;
import com.jhmarryme.pojo.vo.CommentLevelCountsVO;
import com.jhmarryme.utils.PagedGridResult;

import java.util.List;

public interface ItemService {

    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

//    /**
//     * 根据商品规格id获取规格对象的具体信息
//     * @param specId
//     * @return
//     */
//    public ItemsSpec queryItemSpecById(String specId);
//
//    /**
//     * 根据商品id获得商品图片主图url
//     * @param itemId
//     * @return
//     */
//    public String queryItemMainImgById(String itemId);
//
//    /**
//     * 减少库存
//     * @param specId
//     * @param buyCounts
//     */
//    public void decreaseItemSpecStock(String specId, int buyCounts);

    /**
          * 根据商品id查询商品的评价等级数量
          * @param itemId
          */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品的评价（分页）
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult queryPagedComments(String itemId, Integer level,
                                              Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searhItems(String keywords, String sort,
                                              Integer page, Integer pageSize);

    /**
     * 根据分类id搜索商品列表
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searhItems(Integer catId, String sort,
                                      Integer page, Integer pageSize);

//    /**
//     * 根据规格ids查询最新的购物车中商品数据（用于刷新渲染购物车中的商品数据）
//     * @param specIds
//     * @return
//     */
//    public List<ShopcartVO> queryItemsBySpecIds(String specIds);
}
