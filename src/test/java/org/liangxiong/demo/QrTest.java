package org.liangxiong.demo;

import cn.hutool.core.util.ArrayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-04-24 08:36
 * @description 二维码测试类
 **/
@Slf4j
public class QrTest {

    private static byte[] getByteAboutUrlQr(String url, int inputWidth, int inputHeight) {
        Map<EncodeHintType, Object> hints = new HashMap<>(8);
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, inputWidth, inputHeight, hints);
            int[] rec = bitMatrix.getEnclosingRectangle();
            int resWidth = rec[2];
            int resHeight = rec[3];
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
            resMatrix.clear();
            for (int i = 0; i < resWidth; i++) {
                for (int j = 0; j < resHeight; j++) {
                    if (bitMatrix.get(i + rec[0], j + rec[1])) {
                        resMatrix.set(i, j);
                    }
                }
            }
            int width = resMatrix.getWidth();
            int height = resMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, resMatrix.get(x, y) ?
                            Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }
            ImageIO.write(image, "png", out);
            byte[] result = out.toByteArray();
            if (log.isInfoEnabled()) {
                log.info("upload to aliyun oss, file size: {}", ArrayUtil.length(result));
            }
            FileCopyUtils.copy(out.toByteArray(), new File("D:20210404." + "jpg"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        getByteAboutUrlQr("https://blog.liangxiong.org", 100, 100);
    }
}
