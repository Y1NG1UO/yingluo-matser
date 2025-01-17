package com.yingluo.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by heng on 2016/7/16.
 */
@Getter
@AllArgsConstructor
public enum SeckillStatEnum {

	/**
	 *
	 */
	SUCCESS(1, "秒杀成功"), END(0, "秒杀结束"), REPEAT_KILL(-1, "重复秒杀"), INNER_ERROR(-2, "秒杀异常");
	private final int state;
	private final String stateInfo;

	public static SeckillStatEnum stateOf(int index) {
		for (SeckillStatEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
