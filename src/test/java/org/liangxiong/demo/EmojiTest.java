package org.liangxiong.demo;

import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-10 10:30
 * @description
 **/
public class EmojiTest {

    public static void main(String[] args) {
        // convert string to emoji
        String inputText = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
        String emoji = EmojiParser.parseToUnicode(inputText);
        System.out.println(emoji);
        // convert emoji to string
        String inputEmoji = "An 😀awesome 😃string with a few 😉emojis!";
        String text = EmojiParser.parseToAliases(inputEmoji);
        System.out.println(text);
        // check emoji
        System.out.println(EmojiManager.containsEmoji(inputEmoji));
        // print all emoji
        System.out.println(EmojiManager.getAll().size());
    }
}
