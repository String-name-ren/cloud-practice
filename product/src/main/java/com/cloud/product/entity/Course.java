package com.cloud.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.frameworkset.orm.annotation.Column;
import com.frameworkset.orm.annotation.ESId;
import lombok.Data;

import java.lang.annotation.Documented;
import java.util.Date;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-07-31 15:55
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Course {

    @ESId
    private Long courseId;

    private String courseName;

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(dataformat = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

}
