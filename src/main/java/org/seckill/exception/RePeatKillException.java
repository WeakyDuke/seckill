package org.seckill.exception;

/**
 * 重复秒杀异常(运行期异常)
 * @Author: Duke
 * @Description:
 * @Date: Created in 8:24 PM 2018/10/21
 * @Modified By:
 */
public class RePeatKillException extends SeckillException{

    public RePeatKillException(String message) {
        super(message);
    }

    public RePeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
