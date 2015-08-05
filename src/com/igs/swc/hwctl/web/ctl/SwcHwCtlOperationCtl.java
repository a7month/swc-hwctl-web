package com.igs.swc.hwctl.web.ctl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igs.swc.eis.SwcNotExistException;
import com.igs.swc.eis.SwcOfflineException;
import com.igs.swc.eis.ops.HwOperations;
import com.igs.swc.hwctl.web.data.OpResult;

@Controller
@RequestMapping("/swc")
public class SwcHwCtlOperationCtl {

	@Autowired
	private HwOperations hwOperations;

	@RequestMapping
	@ResponseBody
	public Collection<?> getSwcList() {
		return hwOperations.swcList();
	}

	@RequestMapping(value = "/{swcId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, ?> getSwcInfo(@PathVariable String swcId) {
		return hwOperations.boundOpForSwc(swcId).info();
	}

	@RequestMapping(value = "/{swcId}/relays", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, ?> getSwcRelaysInfo(@PathVariable String swcId) {
		return getSwcInfo(swcId);
	}

	// -----------------------------------------------

	// 闭合所有继电器，接通所有电路
	@RequestMapping(value = "/{swcId}/on", method = { RequestMethod.POST })
	@ResponseBody
	public OpResult swcOn(@PathVariable String swcId) {
		hwOperations.boundOpForSwc(swcId).turnOn();
		return OpResult.OK;
	}

	// 打开所有继电器，关闭所有电路
	@RequestMapping(value = "/{swcId}/off", method = { RequestMethod.POST })
	@ResponseBody
	public OpResult swcOff(@PathVariable String swcId) {
		hwOperations.boundOpForSwc(swcId).turnOff();
		return OpResult.OK;
	}

	@RequestMapping(value = "/{swcId}/hwwm/{bitOp}", method = RequestMethod.POST)
	@ResponseBody
	public OpResult swcHwWmOperation(@PathVariable String swcId) {
		return null;
	}

	@RequestMapping(value = "/{swcId}/reset", method = RequestMethod.POST)
	@ResponseBody
	public OpResult swcReset(@PathVariable String swcId) {
		hwOperations.boundOpForSwc(swcId).reset();
		return OpResult.OK;
	}

	@RequestMapping(value = "/{swcId}/restart", method = RequestMethod.POST)
	@ResponseBody
	public OpResult swcRestart(@PathVariable String swcId) {
		hwOperations.boundOpForSwc(swcId).restart();
		return OpResult.OK;
	}

	// --------------------------- 继电器操作

	// 打开继电器（关电路）
	@RequestMapping(value = "/{swcId}/relay/{relayId}/off", method = { RequestMethod.POST })
	@ResponseBody
	public OpResult relayOff(@PathVariable String swcId,
			@PathVariable String relayId) {
		hwOperations.boundOpForSwc(swcId).boundOpForRelay(relayId).turnOff();
		return OpResult.OK;
	}

	// 闭合继电器（开电路）
	@RequestMapping(value = "/{swcId}/relay/{relayId}/on", method = { RequestMethod.POST })
	@ResponseBody
	public OpResult relayOn(@PathVariable String swcId,
			@PathVariable String relayId) {
		hwOperations.boundOpForSwc(swcId).boundOpForRelay(relayId).turnOn();
		return OpResult.OK;
	}

	@RequestMapping(value = "/{swcId}/relay/{relayId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, ?> getRelayInfo(@PathVariable String swcId,
			@PathVariable String relayId) {
		return hwOperations.boundOpForSwc(swcId).boundOpForRelay(relayId)
				.info();
	}

	@RequestMapping(value = "/{swcId}/relay/{relayId}/state/{bitOp}", method = RequestMethod.POST)
	@ResponseBody
	public OpResult relayBitOperation(@PathVariable String swcId,
			@PathVariable String relayId, @PathVariable String bitOp) {
		// TODO
		return null;
	}

	// ------------------------------

	@ExceptionHandler({ SwcOfflineException.class, SwcNotExistException.class })
	@ResponseBody
	public OpResult handleIOException(IOException ex) {
		return null;
	}

}
