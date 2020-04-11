package cn.ekgc.vms.pojo.vo;

import cn.ekgc.vms.util.ConstantUtil;

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

	public VmsPage() {}

	public VmsPage(Integer pageNum, Integer pageSize) {
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
}
