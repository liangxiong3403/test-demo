package org.liangxiong.demo;

import cn.binarywang.tools.generator.*;
import cn.binarywang.tools.generator.bank.BankCardNumberGenerator;
import cn.binarywang.tools.generator.bank.BankCardTypeEnum;
import cn.binarywang.tools.generator.bank.BankNameEnum;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-10 10:19
 * @description
 **/
public class SampleDataTest {

    public static void main(String[] args) {
        // 身份证处理
        System.out.println(ChineseIDCardNumberGenerator.generateIssueOrg());
        System.out.println(ChineseIDCardNumberGenerator.generateValidPeriod());
        System.out.println(ChineseIDCardNumberGenerator.getInstance().generate());
        // 地址
        System.out.println(ChineseAddressGenerator.getInstance().generate());
        // 手机号码
        System.out.println(ChineseMobileNumberGenerator.getInstance().generate());
        // 姓名
        System.out.println(ChineseNameGenerator.getInstance().generate());
        // email
        System.out.println(EmailAddressGenerator.getInstance().generate());
        // bank card
        System.out.println(BankCardNumberGenerator.generate(BankNameEnum.ICBC, BankCardTypeEnum.CREDIT));
    }
}
