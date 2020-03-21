package com.czw.czw.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


/**
 * 二维码 创建人： 创建时间：2015年4月10日
 * 
 * @version
 */
public class TwoDimensionCode {

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 */
	public static void encoderQRCode(String content, String imgPath) {
		encoderQRCode(content, imgPath, "png", 2);
	}



	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 */
	public static void encoderQRCode(String content, String imgPath,
			String imgType) {
		encoderQRCode(content, imgPath, imgType, 2);
	}


	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public static void encoderQRCode(String content, String imgPath,
			String imgType, int size) {
		try {
			BufferedImage bufImg = zxingQRCode(content); // qRCodeCommon(content,
															// imgType, size);
			File directoryFile = new File(imgPath);
			directoryFile.mkdirs();
			File imgFile = new File(imgPath);

			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	


	/**
	 * 生成一维码（128）
	 * 
	 * @param str
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getBarcode(String str, Integer width, Integer height) {
		if (width == null || width < 200) {
			width = 250;
		}
		if (height == null || height < 50) {
			height = 80;
		}
		try {
			// 文字编码
			Hashtable<EncodeHintType, Integer> hints = new Hashtable<EncodeHintType, Integer>();
			hints.put(EncodeHintType.MARGIN, 3);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(str,BarcodeFormat.CODE_128, width, height, hints);
			BufferedImage bi = new BufferedImage(width,height+ 20, BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.getGraphics();
			g.setColor(Color.WHITE);		
			g.fillRect(0, 0, width, height +20);  
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					bi.setRGB(x, y+3, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}
			g.setFont(new Font("宋体", Font.BOLD, 12));
			g.setColor(Color.BLACK);
			g.drawString(str, (width-str.length()*7)/2, height+18);
			g.dispose();
			return bi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	
	
	/**
	 * 生成一维码，写到文件中
	 * 
	 * @param str
	 * @param height
	 * @param file
	 * @throws IOException
	 */
	public static void getBarcodeWriteFile(String str, Integer width,
			Integer height, File file) throws IOException {
		BufferedImage image = getBarcode(str, width, height);
		ImageIO.write(image, "png", file);
	}

	public static void main(String[] args) {
		String imgPath = "F:/a.png";
		/*
		 * String encoderContent = "Hello 大大、小小,welcome to QRCode!" +
		 * "\nMyblog [ http://sjsky.iteye.com ]" +
		 * "\nEMail [ sjsky007@gmail.com ]";
		 */

		String encoderContent = "http://www.baidu.com";
		TwoDimensionCode handler = new TwoDimensionCode();
		handler.encoderQRCode(encoderContent, imgPath, "png");
		// try {
		// OutputStream output = new FileOutputStream(imgPath);
		// handler.encoderQRCode(content, output);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		System.out.println("========encoder success");

		// String decoderContent = handler.decoderQRCode(imgPath);
		System.out.println("解析结果如下：");
		// System.out.println(decoderContent);
		System.out.println("========decoder success!!!");
	}

	public static BufferedImage zxingQRCode(String content) throws Exception {
		int width = 300;
		int height = 300;
		// 二维码的图片格式
		/**
		 * 设置二维码的参数
		 */
		HashMap hints = new HashMap();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);

		BufferedImage image = toBufferedImage(bitMatrix);
		return image;
	}

	private static BufferedImage toBufferedImage(BitMatrix matrix) {

		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}

		return image;
	}
}
