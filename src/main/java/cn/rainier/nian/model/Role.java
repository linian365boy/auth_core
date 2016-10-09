package cn.rainier.nian.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Role implements Serializable {
	private static final long serialVersionUID = -3498056750436845009L;
	private String name;				//角色名
	private String desc;				//角色描述
	private String remarks;				//备注
	private boolean defaultOrNo;		//是否默认角色
	private Date createDate;			//创建角色日期
	
	public Role(){
	}
	
	public Role(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isDefaultOrNo() {
		return defaultOrNo;
	}
	public void setDefaultOrNo(boolean defaultOrNo) {
		this.defaultOrNo = defaultOrNo;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
