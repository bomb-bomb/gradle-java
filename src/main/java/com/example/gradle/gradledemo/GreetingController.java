package com.example.gradle.gradledemo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		 try {
		 	readZipFile("c:\\bbb.zip");
		 } catch (Exception e) {
		 	e.printStackTrace();
		 }
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/")
	public Greeting index(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	public static void readZipFile(String file) throws Exception { 
		ZipFile zf = new ZipFile(new File(file), ZipFile.OPEN_READ, Charset.forName("GBK"));
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in, Charset.forName("GBK"));
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) { 
			if (!ze.isDirectory()) {
				System.out.println("文件 - " + ze.getName() + "：" + ze.getSize() + " bytes");

				String currentFileName = ze.getName().replaceAll(".*/", "");

				System.out.println("文件名称：" + currentFileName);

				System.out.println("==========================");

				// 提取压缩包文件 ------------ start
				File target = new File("c:\\jieya\\" + currentFileName);

				if (!target.getParentFile().exists()) {
					target.getParentFile().mkdirs();
					target = new File(target.getAbsolutePath());
				}

				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(target));
				InputStream is = zf.getInputStream(ze);
				byte[] buffer = new byte[4096];
				int readLen = 0;
				while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
					os.write(buffer, 0, readLen);
					os.flush();
				}
				is.close();
				os.close();
				// 提取压缩包文件 ------------ end
			}
		}

		zin.closeEntry();
		zf.close();
		zin.close();
	}
}