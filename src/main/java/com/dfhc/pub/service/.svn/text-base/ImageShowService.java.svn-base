/**
 * 
 */
package com.dfhc.pub.service;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.quickbundle.tools.support.log.RmLogHelper;
import org.springframework.stereotype.Service;

import com.dfhc.ISystemConstant;

/**
 * 图片展示服务
 * 
 * @author dfhc
 */
@Service
public class ImageShowService {

	/**
	 * 显示图片
	 * 
	 * @param imgFileName
	 * @param response
	 */
	public void show(String imgFileName, HttpServletResponse response) throws Exception {
		OutputStream output = null;
		InputStream imageIn = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			output = response.getOutputStream();
			// 得到图片的文件流
			imageIn = new FileInputStream(new File(imgFileName));
			
			// 得到输出流
			if (imgFileName.toLowerCase().endsWith(".jpg"))
			// 使用编码处理文件流的情况：
			{
				// 设定输出的类型
				response.setContentType(ISystemConstant.JPG);
				// 得到图片的真实路径
				 
				 BufferedImage bimageIn = ImageIO.read(imageIn);
				 ImageIO.write(bimageIn,  "jpeg" , output); 
				// 得到输入的编码器，将文件流进行jpg格式编码

				//JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);

				// 得到编码后的图片对象

				//BufferedImage image = decoder.decodeAsBufferedImage();

				// 得到输出的编码器

				//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);

				//encoder.encode(image);

				// 对图片进行输出编码

			} else if (imgFileName.toLowerCase().endsWith(".gif"))// 不使用编码处理文件流的情况：
			{

				response.setContentType(ISystemConstant.GIF);

				// 文件流

				bis = new BufferedInputStream(imageIn);

				// 输入缓冲流

				bos = new BufferedOutputStream(output);

				// 输出缓冲流

				byte data[] = new byte[4096];

				// 缓冲字节数

				int size = 0;

				size = bis.read(data);

				while (size != -1) {

					bos.write(data, 0, size);

					size = bis.read(data);

				}

				

				bos.flush();


			}else if (imgFileName.toLowerCase().endsWith(".png"))// 不使用编码处理文件流的情况：
			{

				response.setContentType(ISystemConstant.PNG);

				// 文件流

				bis = new BufferedInputStream(imageIn);

				// 输入缓冲流

				bos = new BufferedOutputStream(output);

				// 输出缓冲流

				byte data[] = new byte[4096];

				// 缓冲字节数

				int size = 0;

				size = bis.read(data);

				while (size != -1) {

					bos.write(data, 0, size);

					size = bis.read(data);

				}


				bos.flush();
			}else if (imgFileName.toLowerCase().endsWith(".bmp"))// 不使用编码处理文件流的情况：
				{

					response.setContentType(ISystemConstant.BMP);

					// 文件流

					bis = new BufferedInputStream(imageIn);

					// 输入缓冲流

					bos = new BufferedOutputStream(output);

					// 输出缓冲流

					byte data[] = new byte[4096];

					// 缓冲字节数

					int size = 0;

					size = bis.read(data);

					while (size != -1) {

						bos.write(data, 0, size);

						size = bis.read(data);

					}


					bos.flush();				

			}else if(imgFileName.toLowerCase().endsWith("."+ISystemConstant.DICTIONARY_PDF)){
				response.setContentType(ISystemConstant.PDF); 

				// 文件流

				bis = new BufferedInputStream(imageIn);

				// 输入缓冲流

				bos = new BufferedOutputStream(output);

				// 输出缓冲流

				byte data[] = new byte[4096];

				// 缓冲字节数

				int size = 0;

				size = bis.read(data);

				while (size != -1) {

					bos.write(data, 0, size);

					size = bis.read(data);

				}


				bos.flush();
			}
		}catch(FileNotFoundException e){
			//e.printStackTrace();
			RmLogHelper.error(getClass(), e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if(imageIn!=null){
				imageIn.close();
			}
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(bos!=null){
				bos.close();
			}
			if(bis!=null){
				bis.close();
			}
		}

	}
}
