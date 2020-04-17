package cn.ekgc.vms.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <b>菜单信息实体</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Menu parent;                        // 上级菜单
	private String text;
	private String url;
	private String icon;
	private Integer status;
	private User createUser;
	private Date createTime;
	private User updateUser;
	private Date updateTime;
	private List<Menu> childList;                       // 下级菜单
	private List<Role> roleList;                        // 拥有该菜单的角色列表

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public boolean equals(Object Obj) {
		//如果这个对象是空的，那么返回一个false，不勾选
		if (Obj == null){
			return false;
		}
		//如果这个值等于这个对象，那么返回一个true，勾选
		if (this == Obj){
			return true;
		}
		//判断Menu是否属于Obj类型
		//instanceof属于多态,用于比较这两个属不属于一个类型的，如果属于返回true
		if (Obj instanceof Menu){
		 //所以强制类型转换
			Menu menu = (Menu) Obj;
			return this.getId().equals(menu.getId());

		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
