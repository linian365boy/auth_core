package cn.rainier.nian.dao.base;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @CopyRright (c)2012-20XX:Rainier
 * @Project: auth_core
 * @ModuleID: 
 * @Comments: dao接口
 * @JDK Version Used:<JDK1.6>		
 * @Namespace: cn.rainier.nian.dao.base
 * @Author: 李年
 * @Create Date: 2013-3-28
 * @Modified By: 
 * @Modified Date: 
 * @Why & What is modified: ? <修改原因描述>		
 * @Version:1.0<版本号>
 */
public interface AbstractDao<T,V extends Serializable> extends JpaRepository <T, V>,JpaSpecificationExecutor<T>{
	
}
