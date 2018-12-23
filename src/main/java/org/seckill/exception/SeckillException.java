package org.seckill.exception;

/**
 * 秒杀相关异常
 *
 * @Author: Duke
 * @Description:
 * @Date: Created in 8:29 PM 2018/10/21
 * @Modified By:
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
