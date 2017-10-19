package com.geekcattle.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class image_txt {

	public static String toChar = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
	public static int width = 498, height = 160;

	public static void main(String[] args) throws IOException {
		String inputFileName = "C:\\Users\\dyc\\Desktop\\QQ截图20170905143858.png";
		BufferedImage image = ImageIO.read(new File(inputFileName));
		BufferedImage scaled = getScaledImg(image);
		char[][] array = getImageMatrix(scaled);
		for (char[] cs : array) {
			for (char c : cs) {
				System.out.print(c);
			}
			System.out.println();
		}

	}

	private static BufferedImage getScaledImg(BufferedImage image) {
		BufferedImage rst = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		rst.getGraphics().drawImage(image, 0, 0, width, height, null);
		return rst;
	}
	
	private static char[][] getImageMatrix(BufferedImage img) {
		int w = img.getWidth(), h = img.getHeight();
		char[][] rst = new char[w][h];
		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++) {
				int rgb = img.getRGB(i, j);
				// 注意溢出
				int r = Integer.valueOf(Integer.toBinaryString(rgb).substring(0, 8), 2);
				int g = (rgb & 0xff00) >> 8;
				int b = rgb & 0xff;
				int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);

				// 把int gray转换成char
				int len = toChar.length();
				int base = 256 / len + 1;
				int charIdx = gray / base;
				rst[i][j] = toChar.charAt(charIdx);
			}
		return rst;
	}

	
}
