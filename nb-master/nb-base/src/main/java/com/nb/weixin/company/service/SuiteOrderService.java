/**
 * 
 */
package com.nb.weixin.company.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nb.entity.CrudService;
import com.nb.weixin.company.dao.SuiteOrderDao;
import com.nb.weixin.company.entity.SuiteOrder;

/**
 * @author willie
 *
 */
//@Component("userService")
@Service(value = "suiteOrderService")
@Transactional
public class SuiteOrderService extends CrudService<SuiteOrderDao, SuiteOrder, String>{

}
