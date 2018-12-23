package org.seckill.exception;

/**
 * 秒杀关闭异常
 *
 * @Author: Duke
 * @Description:
 * @Date: Created in 8:27 PM 2018/10/21
 * @Modified By:
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
