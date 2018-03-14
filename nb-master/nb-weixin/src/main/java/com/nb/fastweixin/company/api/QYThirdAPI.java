package com.nb.fastweixin.company.api;

import com.nb.fastweixin.api.response.BaseResponse;
import com.nb.fastweixin.company.api.config.QYAPIConfig;
import com.nb.fastweixin.company.api.entity.QYAgent;
import com.nb.fastweixin.company.api.enums.QYResultType;
import com.nb.fastweixin.company.api.response.GetQYAgentInfoResponse;
import com.nb.fastweixin.company.api.response.GetQYAgentListResponse;
import com.nb.fastweixin.util.BeanUtil;
import com.nb.fastweixin.util.JSONUtil;
import com.nb.fastweixin.util.StrUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 第三方应用接口 ====================================================================
 * 
 * --------------------------------------------------------------------
 * 
 * @author willie
 * @version 1.0.beta
 *          ====================================================================
 */
public class QYThirdAPI extends QYThirdBaseAPI {

	private static final Logger LOG = LoggerFactory.getLogger(QYThirdAPI.class);

	/**
	 * 构造方法，设置apiConfig
	 *
	 * @param config
	 *            微信API配置对象
	 */
	public QYThirdAPI() {
		// super(config);
	}

	/**
	 * 获取第三方应用凭证
	 * 
	 * @param suiteId
	 * @param suiteSecret
	 * @param suiteTicket
	 */
	public Map getSuiteToken(String suiteId, String suiteSecret, String suiteTicket) {
		String url = BASE_API_URL + "cgi-bin/service/get_suite_token";
		
		if (StrUtil.isBlank(suiteId)) {
			LOG.error("suiteId null exception");
			return null;
		}
		if (StrUtil.isBlank(suiteSecret)) {
			LOG.error("suiteSecret null exception");
			return null;
		}
		if (StrUtil.isBlank(suiteTicket)) {
			LOG.error("suiteTicket null exception");
			return null;
		}
		Map param = new HashMap();
		param.put("suite_id", suiteId);
		param.put("suite_secret", suiteSecret);
		param.put("suite_ticket", suiteTicket);
		BaseResponse r = executePost(url, param);

		String jsonResult = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
		return JSONUtil.toBean(jsonResult, Map.class);
		
		
	}
	
	

	/**
	 * 获取全部应用列表
	 * 
	 * @return 应用列表
	 */
	public GetQYAgentListResponse getAll() {
		GetQYAgentListResponse response;
		String url = BASE_API_URL + "cgi-bin/agent/list?access_token=#";
		BaseResponse r = executeGet(url);
		String jsonResult = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
		response = JSONUtil.toBean(jsonResult, GetQYAgentListResponse.class);
		return response;
	}

	/**
	 * 获取应用信息
	 * 
	 * @param agentId
	 *            应用ID
	 * @return 应用信息
	 */
	public GetQYAgentInfoResponse getInfo(String agentId) {
		BeanUtil.requireNonNull(agentId, "agentId is null");
		GetQYAgentInfoResponse response;
		String url = BASE_API_URL + "cgi-bin/agent/get?access_token=#&agentid=" + agentId;
		BaseResponse r = executeGet(url);
		String jsonResult = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
		response = JSONUtil.toBean(jsonResult, GetQYAgentInfoResponse.class);
		return response;
	}

	/**
	 * 设置应用信息 -----------------------------------------------
	 * 设置应用信息方法使用新的update方法代替。 -----------------------------------------------
	 * 
	 * @param agent
	 *            应用对象
	 * @param mediaId
	 *            应用的logo
	 * @return 创建结果
	 */
	@Deprecated
	public QYResultType create(QYAgent agent, String mediaId) {
		String url = BASE_API_URL + "cgi-bin/agent/set?access_token=#";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("agentid", agent.getAgentId());
		params.put("report_location_flag", String.valueOf(agent.getReportLocationFlag()));
		params.put("logo_mediaid", mediaId);
		params.put("name", agent.getName());
		params.put("description", agent.getDescription());
		params.put("redirect_domain", agent.getRedirectDomain());
		params.put("isreportuser", agent.getIsReportUser());
		params.put("isreportenter", agent.getIsReportEnter());
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		return QYResultType.get(response.getErrcode());
	}

	/**
	 * 新的设置应用信息
	 * 
	 * @param params
	 *            参数
	 * @return 返回结果
	 */
	public QYResultType update(Map<String, Object> params) {
		String url = BASE_API_URL + "cgi-bin/agent/set?access_token=#";
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		return QYResultType.get(response.getErrcode());
	}
}
