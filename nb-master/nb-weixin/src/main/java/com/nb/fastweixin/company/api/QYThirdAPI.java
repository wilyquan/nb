package com.nb.fastweixin.company.api;

import com.nb.fastweixin.api.enums.QYAuthType;
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
	 * 该API用于获取预授权码。预授权码用于企业授权时的第三方服务商安全验证。
	 * 
	 * @param suiteAccessToken
	 * @return
	 */
	public Map getPreAuthCode(String suiteAccessToken) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}

		String url = BASE_API_URL + "cgi-bin/service/get_pre_auth_code?suite_access_token=" + suiteAccessToken;

		BaseResponse r = executeGet(url);
		return getResult(r);
	}

	/**
	 * 该接口可对某次授权进行配置。可支持测试模式（应用未发布时）。
	 * 
	 * @param suiteAccessToken
	 * @param preAuthCode
	 * @param authType
	 * @return
	 */
	public Map setSessionInfo(String suiteAccessToken, String preAuthCode, QYAuthType authType) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}
		if (StrUtil.isBlank(preAuthCode)) {
			LOG.error("preAuthCode null exception");
			return null;
		}

		String url = BASE_API_URL + "cgi-bin/service/set_session_info?suite_access_token=" + suiteAccessToken;

		Map param = new HashMap();
		param.put("pre_auth_code", preAuthCode);

		Map sessionInfo = new HashMap();
		param.put("session_info", sessionInfo);

		sessionInfo.put("auth_type", authType);

		BaseResponse r = executePost(url, param);

		return getResult(r);
	}

	/**
	 * 获取企业永久授权码
	 * 
	 * 该API用于使用临时授权码换取授权方的永久授权码，并换取授权信息、企业access_token，临时授权码一次有效。建议第三方优先以userid为主键，其次以email为主键来建立自己的管理员账号。
	 * 
	 * @param suiteAccessToken
	 * @param authCode 临时授权码，会在授权成功时附加在redirect_uri中跳转回第三方服务商网站，或通过回调推送给服务商。长度为64至512个字节
	 * @return
	 */
	public Map getPermanentCode(String suiteAccessToken, String authCode) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}
		if (StrUtil.isBlank(authCode)) {
			LOG.error("authCode null exception");
			return null;
		}

		String url = BASE_API_URL + "cgi-bin/service/get_permanent_code?suite_access_token=" + suiteAccessToken;

		Map param = new HashMap();
		param.put("auth_code", authCode);

		BaseResponse r = executePost(url, param);

		return getResult(r);
	}
	
	/**
	 * 获取企业授权信息
	 * 
	 * 该API用于通过永久授权码换取企业微信的授权信息。 永久code的获取，是通过临时授权码使用get_permanent_code 接口获取到的permanent_code
	 * @param suiteAccessToken
	 * @param cropId 授权方corpid
	 * @param permanentCode 永久授权码，通过get_permanent_code获取
	 * @return
	 */
	public Map getAuthInfo(String suiteAccessToken, String cropId, String permanentCode) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}
		if (StrUtil.isBlank(cropId)) {
			LOG.error("cropId null exception");
			return null;
		}
		if (StrUtil.isBlank(permanentCode)) {
			LOG.error("permanentCode null exception");
			return null;
		}

		String url = BASE_API_URL + "cgi-bin/service/get_auth_info?suite_access_token=" + suiteAccessToken;

		Map param = new HashMap();
		param.put("auth_corpid", cropId);
		param.put("permanent_code", permanentCode);
		
		BaseResponse r = executePost(url, param);

		return getResult(r);
	}
	
	/**
	 * 获取企业access_token
	 * 
	 * 第三方服务商在取得企业的永久授权码后，通过此接口可以获取到企业的access_token。
	 * 获取后可通过通讯录、应用、消息等企业接口来运营这些应用。
	 * 此处获得的企业access_token与企业获取access_token拿到的token，本质上是一样的，只不过获取方式不同。获取之后，就跟普通企业一样使用token调用API接口
	 * 调用企业接口所需的access_token获取方法如下。
	 * 
	 * @param suiteAccessToken
	 * @param cropId
	 * @param permanentCode
	 * @return
	 */
	public Map getCorpAccessToken(String suiteAccessToken, String cropId, String permanentCode) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}
		if (StrUtil.isBlank(cropId)) {
			LOG.error("cropId null exception");
			return null;
		}
		if (StrUtil.isBlank(permanentCode)) {
			LOG.error("permanentCode null exception");
			return null;
		}

		String url = BASE_API_URL + "cgi-bin/service/get_corp_token?suite_access_token=" + suiteAccessToken;

		Map param = new HashMap();
		param.put("auth_corpid", cropId);
		param.put("permanent_code", permanentCode);
		
		BaseResponse r = executePost(url, param);

		return getResult(r);
	}
	
	/**
	 * 第三方服务商可以用此接口获取授权企业中某个第三方应用的管理员列表，以便服务商在用户进入应用主页之后根据是否管理员身份做权限的区分。
	 * 
	 * @param suiteAccessToken
	 * @param cropId
	 * @param agentId
	 * @return
	 */
	public Map getAdminList(String suiteAccessToken, String cropId, String agentId) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}
		if (StrUtil.isBlank(cropId)) {
			LOG.error("cropId null exception");
			return null;
		}
		if (StrUtil.isBlank(agentId)) {
			LOG.error("agentId null exception");
			return null;
		}

		String url = BASE_API_URL + "cgi-bin/service/get_admin_list?suite_access_token=" + suiteAccessToken;

		Map param = new HashMap();
		param.put("auth_corpid", cropId);
		param.put("agentid", agentId);
		
		BaseResponse r = executePost(url, param);

		return getResult(r);
	}
	
	/**
	 * 第三方根据code获取企业成员信息
	 * 
	 * @param suiteAccessToken
	 * @param code
	 * @return
	 */
	public Map getUserInfo3rd(String suiteAccessToken, String code) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}
		if (StrUtil.isBlank(code)) {
			LOG.error("code null exception");
			return null;
		}
		

		String url = BASE_API_URL + "cgi-bin/service/getuserinfo3rd?access_token=" + suiteAccessToken + "&code="+code;

		
		BaseResponse r = executeGet(url);

		return getResult(r);
	}
	
	/**
	 * 第三方使用user_ticket获取成员详情
	 * 
	 * @param suiteAccessToken
	 * @param userTicket
	 * @return
	 */
	public Map getUserDetail3rd(String suiteAccessToken, String userTicket) {

		if (StrUtil.isBlank(suiteAccessToken)) {
			LOG.error("suiteAccessToken null exception");
			return null;
		}
		if (StrUtil.isBlank(userTicket)) {
			LOG.error("code null exception");
			return null;
		}
		

		String url = BASE_API_URL + "cgi-bin/service/getuserdetail3rd?access_token=" + suiteAccessToken;
		Map param = new HashMap();
		param.put("user_ticket", userTicket);
		
		BaseResponse r = executePost(url, param);

		return getResult(r);
	}
	

	private Map getResult(BaseResponse r) {
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
