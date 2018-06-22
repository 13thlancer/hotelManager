package com.whxx.hms.controller.systemcode;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.whxx.core.controller.BaseController;
import com.whxx.core.factory.PageFactory;
import com.whxx.core.utils.CommUtil;
import com.whxx.core.utils.HttpUtil;
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.model.ConsumeCode;
import com.whxx.hms.model.MarketCode;
import com.whxx.hms.model.PayCode;
import com.whxx.hms.model.PriceCode;
import com.whxx.hms.model.RsvMan;
import com.whxx.hms.model.SrcCode;
import com.whxx.hms.service.CustCodeSrv;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/pubCode")
@RestController
public class CustCodeController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(CustCodeController.class);
	@Autowired
	private CustCodeSrv custCodeSrvImpl;

	/*
	 * 获取费用码所有信息 参数:hotelId,hotelGroupId
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/listConsumeCode")
	@ApiOperation(value = "获取费用码所有信息 ", notes = "获取费用码所有信息 ", response = Map.class, httpMethod = "POST")
	public Object listConsumeCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====查询费用码信息参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "费用码信息查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.custCodeSrvImpl.listConsumeCode(paramMap);

		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "费用码信息查询结果", result);
	}

	/*
	 * 获取某一费用码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectConsumeCode")
	public Object selectConsumeCodeByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新付款码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新付款码参数为空", paramMap);
		}
		ConsumeCode consumeCode = this.custCodeSrvImpl.selectConsumeCodeByName(paramMap);
		Map map = new HashMap<>();
		map.put("consumeCode", consumeCode);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "费用码信息查询结果", map);
	}

	/*
	 * 修改费用码信息
	 */
	@RequestMapping("/updateConsumeCode")
	public Object updateConsumeCode(HttpServletRequest request) {
		logger.info("=======费用码信息进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====费用码信息参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "费用码信息参数为空", paramMap);
		}
		ConsumeCode consumeCode = new ConsumeCode();
		BeanUtil.copyProperties(paramMap, consumeCode);
		consumeCode.setUpdateDate(new Date());
		consumeCode.setUpdateEmp(getLoginUserAccount());
		try {
			this.custCodeSrvImpl.updateConsumeCode(consumeCode);
			logger.info("=====费用码修改成功=======");
		} catch (Exception e) {
			logger.error("=====费用码信息修改失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "费用码信息修改失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "费用码信息修改成功", null);
	}

	/*
	 * 获取所有付款码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listPayCode")
	public Object listPayCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====付款码信息查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "付款码信息查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.custCodeSrvImpl.listPayCode(paramMap);
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "付款码信息查询结果", result);
	}

	/*
	 * 获取某一付款码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectPayCode")
	public Object selectPayCodeByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新付款码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新付款码参数为空", paramMap);
		}
		PayCode payCode = this.custCodeSrvImpl.selectPayCodeByName(paramMap);
		Map map = new HashMap<>();
		map.put("payCode", payCode);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "付款码信息查询结果", map);
	}

	/*
	 * 添加新付款码 参数:payCodeId
	 */
	@RequestMapping("/insertPayCode")
	public Object insertPayCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新付款码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新付款码参数为空", paramMap);
		}
		// 判断序列号是否重复
		String seq = request.getParameter("seq");
		String payCodeSeq = this.custCodeSrvImpl.isPayCodeSeq(seq);
		if (payCodeSeq != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "序列号不能重复", null);
		}
		// 判断付款码是否重复
		String payCodeCode = request.getParameter("payCode");
		String payCodeConfigCode = this.custCodeSrvImpl.isPayCodeConfigCode(payCodeCode);
		if (payCodeConfigCode != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "付款码不能重复", null);
		}
		// 新增,seq默认为1
		PayCode payCode = new PayCode();
		String payCodeId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, payCode);
		payCode.setPayCodeId(payCodeId);
		payCode.setStopped("N");// 是否弃用，默认不弃用
		payCode.setLineNo(ToolUtil.getLineNo());
		payCode.setVersion(1);
		try {
			this.custCodeSrvImpl.insertPayCode(setCreateAndUpdate(payCode));
			logger.info("=====添加付款码成功=======");
		} catch (Exception e) {
			logger.error("=====添加付款码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新付款码失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新付款码成功", null);
	}

	/*
	 * 修改付款码信息
	 */
	@RequestMapping("/updatePayCode")
	public Object updatePayCode(HttpServletRequest request) {
		logger.info("=======付款码进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====付款码修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "付款码修改参数为空", paramMap);
		}

		PayCode payCode = new PayCode();
		BeanUtil.copyProperties(paramMap, payCode);
		payCode.setUpdateDate(new Date());
		payCode.setUpdateEmp(getLoginUserAccount());
		try {
			this.custCodeSrvImpl.updatePayCode(payCode);
			logger.info("=====付款码修改成功=======");
		} catch (Exception e) {
			logger.error("=====付款码修改失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "付款码修改失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "付款码修改成功", null);
	}

	/*
	 * 删除付款码 参数:payCodeId
	 */
	@RequestMapping("/deletePayCode/{payCodeId}")
	public Object deletePayCode(HttpServletRequest request, @PathVariable String payCodeId) {
		logger.info("=======删除付款码=======");
		try {
			this.custCodeSrvImpl.deletePayCodeById(payCodeId);
			logger.info("=====删除付款码成功=======");
		} catch (Exception e) {
			logger.error("=====删除付款码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除付款码失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除付款码成功", null);
	}

	/*
	 * 获取所有市场码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listMarketCode")
	public Object listMarketCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====市场码信息查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "市场码信息查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.custCodeSrvImpl.listMarketCode(paramMap);
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "市场码信息查询结果", result);
	}

	/*
	 * 获取某一市场码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectMarketCode")
	public Object selectMarketCodeByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新付款码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新付款码参数为空", paramMap);
		}
		MarketCode marketCode = this.custCodeSrvImpl.selectMarketCodeByName(paramMap);
		Map map = new HashMap<>();
		map.put("marketCode", marketCode);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "市场码信息查询结果", map);
	}

	/*
	 * 添加新市场码 参数:marketCodeId
	 */
	@RequestMapping("/insertMarketCode")
	public Object insertMarketCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新市场码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新市场码参数为空", paramMap);
		}
		// 判断序列号是否重复
		String seq = request.getParameter("seq");
		String marketCodeSeq = this.custCodeSrvImpl.isMarketCodeSeq(seq);
		if (marketCodeSeq != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "序列号不能重复", null);
		}
		// 判断付款码是否重复
		String marketCodeCode = request.getParameter("marketCode");
		String marketCodeConfigCode = this.custCodeSrvImpl.isPayCodeConfigCode(marketCodeCode);
		if (marketCodeConfigCode != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "市场码不能重复", null);
		}
		// 新增,seq默认为1
		MarketCode marketCode = new MarketCode();
		String marketCodeId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, marketCode);
		marketCode.setMarketCodeId(marketCodeId);
		marketCode.setStopped("N");// 是否弃用，默认不弃用
		marketCode.setLineNo(ToolUtil.getLineNo());
		marketCode.setVersion(1);
		try {
			this.custCodeSrvImpl.insertMarketCode(setCreateAndUpdate(marketCode));
			logger.info("=====添加新市场码成功=======");
		} catch (Exception e) {
			logger.error("=====添加新市场码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新市场码失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新市场码成功", null);
	}

	/*
	 * 修改市场码信息
	 */
	@RequestMapping("/updateMarketCode")
	public Object updateMarketCode(HttpServletRequest request) {
		logger.info("=======市场码进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====市场码修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "市场码修改参数为空", paramMap);
		}

		MarketCode marketCode = new MarketCode();
		BeanUtil.copyProperties(paramMap, marketCode);
		marketCode.setUpdateDate(new Date());
		marketCode.setUpdateEmp(getLoginUserAccount());
		try {
			this.custCodeSrvImpl.updateMarketCode(marketCode);
			logger.info("=====市场码修改成功=======");
		} catch (Exception e) {
			logger.error("=====市场码修改失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "市场码修改失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "市场码修改成功", null);
	}

	/*
	 * 删除市场码 参数:marketCodeId
	 */
	@RequestMapping("/deleteMarketCode/{marketCodeId}")
	public Object deleteMarketCode(HttpServletRequest request, @PathVariable String marketCodeId) {
		logger.info("=======删除市场码=======");
		try {
			this.custCodeSrvImpl.deleteMarketCodeById(marketCodeId);
			logger.info("=====删除市场码成功=======");
		} catch (Exception e) {
			logger.error("=====删除市场码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除市场码失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除市场码成功", null);
	}
	
	/*
	 * 获取所有来源码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listSrcCode")
	public Object listSrcCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====来源码信息查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "来源码信息查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.custCodeSrvImpl.listSrcCode(paramMap);
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "来源码信息查询结果", result);
	}

	/*
	 * 获取某一来源码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectSrcCode")
	public Object selectSrcCodeByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新付款码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新付款码参数为空", paramMap);
		}
		SrcCode srcCode = this.custCodeSrvImpl.selectSrcCodeByName(paramMap);
		Map map = new HashMap<>();
		map.put("srcCode", srcCode);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "来源码信息查询结果", map);
	}

	/*
	 * 添加新来源码 参数:srcCodeId
	 */
	@RequestMapping("/insertSrcCode")
	public Object insertSrcCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新来源码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新来源码参数为空", paramMap);
		}
		// 判断序列号是否重复
		String seq = request.getParameter("seq");
		String srcCodeSeq = this.custCodeSrvImpl.isSrcsCodeSeq(seq);
		if (srcCodeSeq != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "序列号不能重复", null);
		}
		// 判断来源码是否重复
		String srcCodeCode = request.getParameter("srcCode");
		String srcCodeConfigCode = this.custCodeSrvImpl.isSrcCodeConfigCode(srcCodeCode);
		if (srcCodeConfigCode != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "来源码不能重复", null);
		}
		// 新增seq默认为1
		SrcCode srcCode = new SrcCode();
		String srcCodeId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, srcCode);
		srcCode.setSrcCodeId(srcCodeId);
		srcCode.setStopped("N");// 是否弃用，默认不弃用
		srcCode.setLineNo(ToolUtil.getLineNo());
		srcCode.setVersion(1);
		try {
			this.custCodeSrvImpl.insertSrcCode(setCreateAndUpdate(srcCode));
			logger.info("=====添加新来源成功=======");
		} catch (Exception e) {
			logger.error("=====添加新来源码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新来源码失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新来源码成功", null);
	}

	/*
	 * 修改来源码信息
	 */
	@RequestMapping("/updateSrcCode")
	public Object updateSrcCode(HttpServletRequest request) {
		logger.info("=======来源码进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====来源吗修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "来源码修改参数为空", paramMap);
		}

		SrcCode srcCode = new SrcCode();
		BeanUtil.copyProperties(paramMap, srcCode);
		srcCode.setUpdateDate(new Date());
		srcCode.setUpdateEmp(getLoginUserAccount());
		try {
			this.custCodeSrvImpl.updateSrcCode(srcCode);
			logger.info("=====来源码修改成功=======");
		} catch (Exception e) {
			logger.error("=====来源码修改失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "来源码修改失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "来源码修改成功", null);
	}

	/*
	 * 删除来源码 参数:srcCodeId
	 */
	@RequestMapping("/deleteSrcCode/{srcCodeId}")
	public Object deleteSrcCode(HttpServletRequest request, @PathVariable String srcCodeId) {
		logger.info("=======删除来源码=======");
		try {
			this.custCodeSrvImpl.deletSrcCodeById(srcCodeId);
			logger.info("=====删除来源码成功=======");
		} catch (Exception e) {
			logger.error("=====删除来源码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除来源码失败", null);
		}

		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除来源码成功", null);
	}
	
	
	/*
	 * 获取所有房价码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listPriceCode")
	public Object listPriceCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====房价信息查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "房价信息查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.custCodeSrvImpl.listPriceCode(paramMap);
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房价码信息查询结果", result);
	}
	
	/*
	 * 获取某一房价码信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectPriceCode")
	public Object selectPriceCodeByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新房价码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加房价款码参数为空", paramMap);
		}
		PriceCode priceCode = this.custCodeSrvImpl.selectPriceCodeByName(paramMap);
		Map map = new HashMap<>();
		map.put("priceCode", priceCode);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房价码信息查询结果", map);
	}
	
	/*
	 * 添加新房价码 参数
	 */
	@RequestMapping("/insertPriceCode")
	public Object insertPriceCode(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新房价码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新房价码参数为空", paramMap);
		}
	
		// 判断房价码是否重复
		String priceCodeCode = request.getParameter("priceCode");
		String priceCodeConfigCode = this.custCodeSrvImpl.isPriceCodeConfigCode(priceCodeCode);
		if (priceCodeConfigCode != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "房价码不能重复", null);
		}
		PriceCode priceCode = new PriceCode();
		String priceCodeId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, priceCodeId);
		priceCode.setPriceCodeId(priceCodeId);
		priceCode.setStopped("N");// 是否弃用，默认不弃用
		priceCode.setLineNo(ToolUtil.getLineNo());
		priceCode.setVersion(1);
		try {
			this.custCodeSrvImpl.insertPriceCode(setCreateAndUpdate(priceCode));
			logger.info("=====添加新房价成功=======");
		} catch (Exception e) {
			logger.error("=====添加新房价码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新房价码失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新房价码成功", null);
	}
	
	/*
	 * 修改房价码信息
	 */
	@RequestMapping("/updatePriceCode")
	public Object updatePriceCode(HttpServletRequest request) {
		logger.info("=======房价码进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====房价吗修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "房价码修改参数为空", paramMap);
		}
		
		PriceCode priceCode = new PriceCode();
		BeanUtil.copyProperties(paramMap, priceCode);
		priceCode.setUpdateDate(new Date());
		priceCode.setUpdateEmp(getLoginUserAccount());
		try {
			this.custCodeSrvImpl.updatePriceCode(priceCode);
			logger.info("=====房价码修改成功=======");
		} catch (Exception e) {
			logger.error("=====房价码修改失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "房价码修改失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "房价码修改成功", null);
	}
	
	/*
	 * 删除房价码 参数:srcCodeId
	 */
	@RequestMapping("/deletePriceCode/{priceCodeId}")
	public Object deletePriceCode(HttpServletRequest request, @PathVariable String priceCodeId) {
		logger.info("=======删除房价码=======");
		try {
			this.custCodeSrvImpl.deletePriceCodeById(priceCodeId);
			logger.info("=====删除房价码成功=======");
		} catch (Exception e) {
			logger.error("=====删除房价码失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除房价码失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除房价码成功", null);
	}
	
	/*
	 * 获取所有协议单位信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listRsvMan")
	public Object listRsvMan(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====协议单位信息查询参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "协议单位信息查询参数为空", paramMap);
		}
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.custCodeSrvImpl.listRsvMan(paramMap);
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", pageInfo.getTotal());
		result.put("rows", pageList);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "协议单位信息查询结果", result);
	}
	
	/*
	 * 获取某一协议单位信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectRsvMan")
	public Object selectRsvManByName(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====获取新协议单位参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "获取协议单位参数为空", paramMap);
		}
		RsvMan rsvMan = this.custCodeSrvImpl.selectRsvManByName(paramMap);
		Map map = new HashMap<>();
		map.put("rsvMan", rsvMan);
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "协议单位信息查询结果", map);
	}
	
	/*
	 * 添加协议单位 参数
	 */
	@RequestMapping("/insertRsvMan")
	public Object insertRsvMan(HttpServletRequest request) {
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====添加新协议单位码参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "添加新协议单位参数为空", paramMap);
		}
	
		// 判断协议单位是否重复
		String memberNo = request.getParameter("memberNo");
		String distinctNo = this.custCodeSrvImpl.isDistinctNo(memberNo);
		if (distinctNo != null) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "协议单位不能重复", null);
		}
		RsvMan rsvMan = new RsvMan();
		String rsvManId = UUIDUtil.getUUID();
		BeanUtil.copyProperties(paramMap, rsvManId);
		rsvMan.setRsvManId(rsvManId);
		//rsvMan.setStopped("N");// 是否弃用，默认不弃用
		rsvMan.setLineNo(ToolUtil.getLineNo());
		rsvMan.setVersion(1);
		try {
			this.custCodeSrvImpl.insertRsvMan(setCreateAndUpdate(rsvMan));
			logger.info("=====添加新协议单位成功=======");
		} catch (Exception e) {
			logger.error("=====添加新协议单位失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "添加新协议单位失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "添加新协议单位成功", null);
	}
	
	/*
	 * 修改协议单位信息
	 */
	@RequestMapping("/updateRsvMan")
	public Object updateRsvMan(HttpServletRequest request) {
		logger.info("=======协议单位进行修改=======");
		Map<String, String> paramMap = HttpUtil.getParamMap(request);
		logger.info("=====协议单位修改参数paramMap===" + JSONObject.toJSONString(paramMap));
		if (null == paramMap || paramMap.isEmpty()) {
			return CommUtil.setMessage(HttpStatus.HTTP_MULT_CHOICE, "协议单位修改参数为空", paramMap);
		}
		
		RsvMan rsvMan = new RsvMan();
		BeanUtil.copyProperties(paramMap, rsvMan);
		rsvMan.setUpdateDate(new Date());
		rsvMan.setUpdateEmp(getLoginUserAccount());
		try {
			this.custCodeSrvImpl.updateRsvMan(rsvMan);
			logger.info("=====协议单位修改成功=======");
		} catch (Exception e) {
			logger.error("=====协议单位修改失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "协议单位修改失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "协议单位修改成功", null);
	}
	
	/*
	 * 删除协议单位 参数:主键
	 */
	@RequestMapping("/deleteRsvMan/{rsvManId}")
	public Object deleteRsvMan(HttpServletRequest request, @PathVariable String rsvManId) {
		logger.info("=======删除协议单位=======");
		try {
			this.custCodeSrvImpl.deleteRsvMan(rsvManId);
			logger.info("=====删除协议单位成功=======");
		} catch (Exception e) {
			logger.error("=====删除协议单位失败=======" + e.getMessage(), e);
			return CommUtil.setMessage(HttpStatus.HTTP_INTERNAL_ERROR, "删除协议单位失败", null);
		}
		
		return CommUtil.setMessage(HttpStatus.HTTP_OK, "删除协议单位成功", null);
	}
	
}
