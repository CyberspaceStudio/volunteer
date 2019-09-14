package com.volunteer.volunteer.util;

/**
 * @author ：Maolin
 * @className ：EmojiCharacterUtil
 * @date ：Created in 2019/8/19 20:54
 * @description： 将带有emoji的字符串，格式化成unicode字符串，并且提供可见unicode字符反解成emoji字符
 * @version: 1.0
 */

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * 本类的主要功能是将带有emoji的字符串，格式化成unicode字符串，并且提供可见unicode字符反解成emoji字符
 *
 *
 * 相关识知点：
 * <b>
 * Unicode平面，
 * BMP的字符可以使用charAt(index)来处理,计数可以使用length()
 * 其它平面字符，需要用codePointAt(index),计数可以使用codePointCount(0,str.lenght())</b>
 *
 * Unicode可以逻辑分为17平面（Plane），每个平面拥有65536（ = 216）个代码点，虽然目前只有少数平面被使
 * 用。
 * 平面0 (0000–FFFF): 基本多文种平面（Basic Multilingual Plane, BMP）.
 * 平面1 (10000–1FFFF): 多文种补充平面（Supplementary Multilingual Plane, SMP）.
 * 平面2 (20000–2FFFF): 表意文字补充平面（Supplementary Ideographic Plane, SIP）.
 * 平面3 (30000–3FFFF): 表意文字第三平面（Tertiary Ideographic Plane, TIP）.
 * 平面4 to 13 (40000–DFFFF)尚未使用
 * 平面14 (E0000–EFFFF): 特别用途补充平面（Supplementary Special-purpose Plane, SSP）
 * 平面15 (F0000–FFFFF)保留作为私人使用区（Private Use Area, PUA）
 * 平面16 (100000–10FFFF)，保留作为私人使用区（Private Use Area, PUA）
 *
 * 参考：
 * 维基百科: http://en.wikipedia.org/wiki/Emoji
 * GITHUB: http://punchdrunker.github.io/iOSEmoji/
 * 杂项象形符号:1F300-1F5FF
 * 表情符号：1F600-1F64F
 * 交通和地图符号:1F680-1F6FF
 * 杂项符号：2600-26FF
 * 符号字体:2700-27BF
 * 国旗：1F100-1F1FF
 * 箭头：2B00-2BFF 2900-297F
 * 各种技术符号：2300-23FF
 * 字母符号: 2100–214F
 * 中文符号： 303D 3200–32FF 2049 203C
 *  Private Use Area:E000-F8FF;
 *  High Surrogates D800..DB7F;
 *  High Private Use Surrogates  DB80..DBFF
 *  Low Surrogates DC00..DFFF  D800-DFFF E000-F8FF
 *  标点符号：2000-200F 2028-202F 205F 2065-206F
 *  变异选择器：IOS独有 FE00-FE0F
 * </pre>
 */
public class EmojiCharacterUtil {

    // 转义时标识
    private static final char unicode_separator = '&';
    private static final char unicode_prefix = 'u';
    private static final char separator = ':';

    private static boolean isEmojiCharacter(int codePoint) {
        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
                || codePoint == 0x303D
                || codePoint == 0x2049
                || codePoint == 0x203C
                || (codePoint >= 0x2000 && codePoint <= 0x200F)//
                || (codePoint >= 0x2028 && codePoint <= 0x202F)//
                || codePoint == 0x205F //
                || (codePoint >= 0x2065 && codePoint <= 0x206F)//
                /* 标点符号占用区域 */
                || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
                || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
                || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
                || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
                || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
    }

    /**
     * 将带有emoji字符的字符串转换成可见字符标识
     */
    public static String escape(String src) {
        if (StringUtils.isBlank(src)) {
            return src;
        }
        int cpCount = src.codePointCount(0, src.length());
        int firCodeIndex = src.offsetByCodePoints(0, 0);
        int lstCodeIndex = src.offsetByCodePoints(0, cpCount - 1);
        StringBuilder sb = new StringBuilder(src.length());
        for (int index = firCodeIndex; index <= lstCodeIndex; index ++) {
            int codepoint = src.codePointAt(index);
            if (isEmojiCharacter(codepoint)) {
                String hash = Integer.toHexString(codepoint);
                sb.append(unicode_separator).append(hash.length()).append(unicode_prefix).append(separator).append(hash);
                // hash 长度，4位1个字节
                index += (hash.length() - 1)/4;
            } else {
                sb.append((char) codepoint);
            }
        }
        return sb.toString();
    }

    /** 解析可见字符标识字符串 */
    public static String reverse(String src) {
        // 查找对应编码的标识位
        if (StringUtils.isBlank(src)) {
            return src;
        }
        StringBuilder sb = new StringBuilder(src.length());
        char[] sourceChar = src.toCharArray();
        int index = 0;
        while (index < sourceChar.length) {
            if (sourceChar[index] == unicode_separator) {
                if (index + 6 >= sourceChar.length) {
                    sb.append(sourceChar[index]);
                    index++;
                    continue;
                }
                // 自已的格式，与通用unicode格式不能互转
                if (sourceChar[index + 1] >= '4' && sourceChar[index + 1] <= '6'
                        && sourceChar[index + 2] == unicode_prefix
                        && sourceChar[index + 3] == separator) {
                    int length = Integer.parseInt(String.valueOf(sourceChar[index + 1]));
                    char[] hexchars = new char[length]; // 创建一个4至六位的数组，来存储uncode码的HEX值
                    for (int j = 0; j < length; j++) {
                        char ch = sourceChar[index + 4 + j];// 4位识别码
                        if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f')) {
                            hexchars[j] = ch;

                        } else { // 字符范围不对
                            sb.append(sourceChar[index]);
                            index++;
                            break;
                        }
                    }
                    sb.append(Character.toChars(Integer.parseInt(new String(hexchars), 16)));
                    index += (4 + length);// 4位前缀+4-6位字符码
                } else if (sourceChar[index + 1] == unicode_prefix) { // 通用字符的反转
                    // 因为第二平面之上的，已经采用了我们自己转码格式，所以这里是固定的长度4
                    char[] hexchars = new char[4];
                    for (int j = 0; j < 4; j++) {
                        char ch = sourceChar[index + 2 + j]; // 两位识别码要去掉
                        if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f')) {
                            hexchars[j] = ch; // 4位识别码
                        } else { // 字符范围不对
                            sb.append(sourceChar[index]);
                            index++;
                            break;
                        }
                        sb.append(Character.toChars(Integer.parseInt(String.valueOf(hexchars), 16)));
                        index += (2 + 4);// 2位前缀+4位字符码
                    }
                } else {
                    sb.append(sourceChar[index]);
                    index++;
                    continue;
                }
            } else {
                sb.append(sourceChar[index]);
                index++;
                continue;
            }
        }

        return sb.toString();
    }

    public static String filter(String src) {
        if (src == null) {
            return null;
        }
        int cpCount = src.codePointCount(0, src.length());
        int firCodeIndex = src.offsetByCodePoints(0, 0);
        int lstCodeIndex = src.offsetByCodePoints(0, cpCount - 1);
        StringBuilder sb = new StringBuilder(src.length());
        for (int index = firCodeIndex; index <= lstCodeIndex;) {
            int codepoint = src.codePointAt(index);
            if (!isEmojiCharacter(codepoint)) {
                //System.err.println("codepoint:" + Integer.toHexString(codepoint));
                sb.append((char) codepoint);
            }
            index += ((Character.isSupplementaryCodePoint(codepoint)) ? 2 : 1);

        }
        return sb.toString();
    }
}