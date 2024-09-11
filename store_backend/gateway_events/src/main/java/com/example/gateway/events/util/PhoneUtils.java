package com.example.gateway.events.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PhoneUtils {
    private static final Pattern VALID_PHONE_PATTERN = Pattern.compile("^(([+]79|89)\\d{9}|\\d{10})$");
    private static final Pattern SIMPLE_PHONE_PATTERN = Pattern.compile("^\\d{10}$");

    /**
     * Проверяет номер телефона.
     * Regex объяснение:
     * 1. Номер должен начинаться с префикса +79 или 89, который дополняется 9 цифрами.
     * 2. Номер должен состоять ровно из 10 цифр.
     *
     * @param phone номер телефона
     * @return true, если номер правильный, иначе false
     */
    public static boolean validatePhone(String phone) {
        if (phone == null) {
            return false;
        }

        Matcher matcher = VALID_PHONE_PATTERN.matcher(phone);
        if (!matcher.matches()) {
            log.error("Неправильный телефонный номер: {}", phone);
            return false;
        }
        return true;
    }

    /**
     * Стандартизирует номер телефона, извлекая последние 10 цифр начиная с первой 9.
     *
     * @param phone номер телефона
     * @return стандартизированный номер телефона в Long
     * @throws NumberFormatException если номер телефона неправильного формата
     */
    public static String normalizePhone(String phone) {
        if (phone == null) {
            return null;
        }

        Matcher matcher = SIMPLE_PHONE_PATTERN.matcher(phone);
        if (!matcher.matches()) {
            int indexOfNine = phone.indexOf('9');
            if (indexOfNine != -1 && phone.length() - indexOfNine == 10) {
                phone = phone.substring(indexOfNine);
            } else {
                log.error("Не удалось стандартизировать телефонный номер: {}", phone);
                throw new NumberFormatException("Неправильный номер телефона:" + phone);
            }
        }
        return phone;
    }
}