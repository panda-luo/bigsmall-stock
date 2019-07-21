package club.panda1024.stock.model.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Wrapper<T> implements Serializable {

    private int status;
    private String message;
    private T data;

}
