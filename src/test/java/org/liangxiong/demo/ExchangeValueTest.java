package org.liangxiong.demo;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-06-11 08:12
 * @description 交换两个变量的值
 **/
public class ExchangeValueTest {

    public static void main(String[] args) {
        // 0000 0000 0000 0000 0000 0000 0000 1010
        int a = 10;
        // 0000 0000 0000 0000 0000 0000 0001 0100
        int b = 20;
        /*
         * 0000 0000 0000 0000 0000 0000 0001 1110
         */
        System.out.println(a ^ b);//30
        /*
         * 0000 0000 0000 0000 0000 0000 0001 1110
         * 0000 0000 0000 0000 0000 0000 0000 1010
         *
         * 0000 0000 0000 0000 0000 0000 0001 0100
         */
        System.out.println(a ^ b ^ a);//b=20
        /*
         * 0000 0000 0000 0000 0000 0000 0001 1110
         * 0000 0000 0000 0000 0000 0000 0001 0100
         *
         * 0000 0000 0000 0000 0000 0000 0000 1010
         */
        System.out.println(a ^ b ^ b);//a=10

        // 密文
        a = a ^ b;
        // 密文+密钥=明文 a^b^b=a
        b = a ^ b;
        // 密文+密钥=明文 (a^b)^a=(a^b)^b=a^b(上一步操作时b=a了)
        a = a ^ b;
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }
}
