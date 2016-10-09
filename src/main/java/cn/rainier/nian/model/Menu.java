package cn.rainier.nian.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Menu implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;			//菜单名，展示在菜单栏上的信息
	private String mark;			//权限分配时显示的字，可能跟name一致，也可能不一致，只是为了分配权限时看得明白，清楚。
	private String url;				//点击菜单的链接
	private Integer priority;		//菜单展示的优先级
	private Integer pId;		//父菜单
	
	public Menu(){
	}
	
	public Menu(String name,boolean leafOrNo,String url,Integer priority){
		this.name = name;
		this.url = url;
		this.priority = priority;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}
