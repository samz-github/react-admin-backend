package com.sam.backendv2.entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseData<T> implements Serializable {
    private T data;
}
