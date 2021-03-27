package org.liangxiong.demo.entity;


import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2020-08-30 20:39
 * @description
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Person extends BaseEntity {

    private String title;

    private String firstName;

    private String lastName;

}
