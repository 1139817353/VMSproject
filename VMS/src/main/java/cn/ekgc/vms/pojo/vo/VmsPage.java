package cn.ekgc.vms.pojo.vo;

import cn.ekgc.vms.util.ConstantUtil;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * <b>分页视图</b>
 * @param <E>
 */
public class VmsPage<E> implements Serializable {
	private static final long seriaVersionUID = 1L;
	private  Integer pageNum;      //当前页码
	private  Integer pageSize;     //每页显示数量
	private  List<E> list;         //分页列表
	private  Long totalCount;      //总数量
	private  Long totalPage;       //总页数
    private  Integer draw;          //用于DataTable 插件
	public VmsPage() {}

	public VmsPage(Integer pageNum, Integer pageSize,Integer draw) {
         if (pageNum != null && pageNum > 0 ){
         	this.pageNum = pageNum;
         }else {
         	this.pageNum = ConstantUtil.PAGE_NUM;
         }
         if (pageSize != null && pageSize > 0 ){
         	this.pageSize = pageSize;
         }else {
         	this.pageSize = ConstantUtil.PAGE_SIZE;
         }
         this.draw = draw;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	/**
	 * <b>将PageHelper信息转换到对象中</b>
	 * @param page
	 */
	public void copyFromPageInfo(PageInfo<E> page) {
	this.list = page.getList();
	this.totalPage = (long) page.getPages();
	this.totalCount = page.getTotal();
	}
}
