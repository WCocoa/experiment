package com.qiantang.neighbourmother.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class QLBitmapUtil {

	public static byte[] BitmapToByte(Bitmap bitmap) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		byte[] array = out.toByteArray();
		return array;
	}

	public static byte[] BitmapToByte(final Bitmap bmp, final boolean needRecycle) {

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.JPEG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Bitmap getBitmapFromByte(byte[] data) {
		if (data == null) {
			return null;
		}
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		return bitmap;
	}

	public static Bitmap getBitmapThumbnail(String path, int width, int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		opts.inSampleSize = Math.max((int) (opts.outHeight / (float) height),
				(int) (opts.outWidth / (float) width));
		opts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(path, opts);
		return bitmap;
	}

	public static Bitmap getBitmapThumbnail(Bitmap bmp, int width, int height) {
		Bitmap bitmap = null;
		if (bmp != null) {
			int bmpWidth = bmp.getWidth();
			int bmpHeight = bmp.getHeight();
			if (width != 0 && height != 0) {
				Matrix matrix = new Matrix();
				float scaleWidth = ((float) width / bmpWidth);
				float scaleHeight = ((float) height / bmpHeight);
				matrix.postScale(scaleWidth, scaleHeight);
				bitmap = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight,
						matrix, true);
			} else {
				bitmap = bmp;
			}
		}
		return bitmap;
	}

	public static Bitmap getBitmapFromRaw(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		InputStream is = context.getResources().openRawResource(resId);
		Bitmap bmp = BitmapFactory.decodeStream(is, null, opt);
		return bmp;
	}

	public static Bitmap getBitmapFromRaw(Context context, int resId,
										  int sampleSize) {
		InputStream is = context.getResources().openRawResource(resId);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = sampleSize;
		return BitmapFactory.decodeStream(is, null, options);
	}

	public static Bitmap readBitmapByIdFromRaw(Context context, int drawableId,
											   int screenWidth, int screenHight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.ARGB_8888;
		options.inInputShareable = true;
		options.inPurgeable = true;
		InputStream stream = context.getResources().openRawResource(drawableId);
		Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
		return getBitmap(bitmap, screenWidth, screenHight);
	}

	public static Bitmap roundCorners(final Bitmap source, final float radius) {

		int width = source.getWidth();

		int height = source.getHeight();

		Paint paint = new Paint();

		paint.setAntiAlias(true);

		paint.setColor(android.graphics.Color.WHITE);

		Bitmap clipped = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(clipped);

		canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius,
				paint);

		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC_IN));

		canvas.drawBitmap(source, 0, 0, paint);

		source.recycle();

		return clipped;
	}

	/**
	 *
	 * @param uri
	 * @param requestCode
	 * @param context
	 */
	public static void startPhotoZoom(Uri uri, int requestCode, Activity context) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 开启剪切
		intent.putExtra("crop", "true");
		// 剪切的宽高比
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 保存图片的宽和高
		intent.putExtra("outputX", 93);
		intent.putExtra("outputY", 93);
		intent.putExtra("return-data", true);

		context.startActivityForResult(intent, requestCode);
	}

	public static void decorateImageView(Bitmap bitmap, int width, int height,
										 int adii, ImageView imageView) {

		Shape shpRound = new RoundRectShape(new float[] { adii, adii, adii,
				adii, adii, adii, adii, adii }, null, null);
		ShapeDrawable dwbRound = new ShapeDrawable(shpRound);
		dwbRound.setIntrinsicWidth(width);
		dwbRound.setIntrinsicHeight(height);
		Shader shdBitmap = new BitmapShader(bitmap, Shader.TileMode.MIRROR,
				Shader.TileMode.MIRROR);
		Matrix matrix = new Matrix();
		matrix.setScale((float) width / bitmap.getWidth(), (float) height
				/ bitmap.getHeight());
		shdBitmap.setLocalMatrix(matrix);
		dwbRound.getPaint().setShader(shdBitmap);
		dwbRound.getPaint().setFlags(
				dwbRound.getPaint().getFlags() | Paint.ANTI_ALIAS_FLAG);

		imageView.setImageDrawable(dwbRound);
	}

	public static Drawable loadDrawableFromUrl(String url, String path_name) {

		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();

			File myFileTemp = new File(path_name);
			if (!myFileTemp.exists()) {

				FileOutputStream fos = new FileOutputStream(myFileTemp);

				byte[] b = new byte[1024];
				int aa;
				while ((aa = i.read(b)) != -1) {
					fos.write(b, 0, aa);
				}

				fos.close();
				i.close();
			}

			return Drawable.createFromPath(path_name);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap loadBitmapFromUrl(String url, String path_name) {

		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();

			File myFileTemp = new File(path_name);
			if (!myFileTemp.exists()) {

				FileOutputStream fos = new FileOutputStream(myFileTemp);

				byte[] b = new byte[1024];
				int aa;
				while ((aa = i.read(b)) != -1) {
					fos.write(b, 0, aa);
				}

				fos.close();
				i.close();
			}

			return BitmapFactory.decodeFile(path_name);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean saveBitmapFromUrl(String url, String path_name) {

		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();

			File myFileTemp = new File(path_name);
			if (!myFileTemp.exists()) {

				FileOutputStream fos = new FileOutputStream(myFileTemp);

				byte[] b = new byte[1024];
				int aa;
				while ((aa = i.read(b)) != -1) {
					fos.write(b, 0, aa);
				}

				fos.close();
				i.close();
				return true;
			}

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static Bitmap getbitmap(String imageUri) {
		Bitmap bitmap = null;
		try {
			URL myFileUrl = new URL(imageUri);
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
			// Log.i(FinalList.DEBUG, "image download finished." + imageUri);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	/**
	 *
	 * @param imageUri
	 * @return
	 * @throws MalformedURLException
	 */
	public static Bitmap getbitmapAndwrite(String imageUri, File file)
			throws Exception {
		Bitmap bitmap = null;

		URL myFileUrl = new URL(imageUri);
		HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
		conn.setDoInput(true);
		conn.connect();

		InputStream is = conn.getInputStream();
		BufferedOutputStream bos = null;
		bos = new BufferedOutputStream(new FileOutputStream(file));

		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = is.read(buf)) > 0) {
			bos.write(buf, 0, len);
		}

		is.close();
		bos.close();

		bitmap = BitmapFactory.decodeFile(file.getCanonicalPath());

		return bitmap;
	}

	public static Bitmap getBitmapFromResource(Context context, int resId,
											   int rwidth, int rhight) {
		Bitmap bit = BitmapFactory
				.decodeResource(context.getResources(), resId);

		int width = bit.getWidth();
		int height = bit.getHeight();

		float scaleWidth = ((float) rwidth) / width;
		float scaleHeight = ((float) rhight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbm = Bitmap.createBitmap(bit, 0, 0, width, height, matrix,
				true);
		return newbm;
	}

	public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
								   int screenHight) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = (float) screenWidth / w;
		float scaleHight = (float) screenHight / h;

		matrix.postScale(scaleWidth, scaleHight);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	public static Bitmap getBitmap(String srcPath, int screenWidth,
								   int screenHight) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		int be = 1;
		if (w > h && w > screenWidth) {
			be = (int) (newOpts.outWidth / screenWidth);
		} else if (w < h && h > screenHight) {
			be = (int) (newOpts.outHeight / screenHight);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return bitmap;
	}

	public static Bitmap compressBitmap(Bitmap image, int kByte) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > kByte) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	private Bitmap compressBitmap(String srcPath, int kByte, int screenWidth,
								  int screenHight) {
		return compressBitmap(getBitmap(srcPath, screenWidth, screenHight),
				kByte);// 压缩好比例大小后再进行质量压缩
	}

	public Bitmap loadFromSdCard(String filePath, int inSampleSize) {
		File file = new File(filePath);
		Bitmap bmp = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		opts.inSampleSize = inSampleSize;

		BitmapFactory.decodeFile(filePath, opts);
		opts.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(filePath, opts);
		if (bmp != null) {
			return bmp;
		}
		return null;
	}

	public static String getAbsoluteImagePath(Activity context, Uri uri) {
		String imagePath = "";
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = context.managedQuery(uri, proj, // Which columns to
														// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)

		if (cursor != null) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			if (cursor.getCount() > 0 && cursor.moveToFirst()) {
				imagePath = cursor.getString(column_index);
			}
		}

		return imagePath;
	}

	public static Bitmap loadImgThumbnail(Activity context, String imgName,
										  int kind) {
		Bitmap bitmap = null;

		String[] proj = { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DISPLAY_NAME };

		Cursor cursor = context.managedQuery(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj,
				MediaStore.Images.Media.DISPLAY_NAME + "='" + imgName + "'",
				null, null);

		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			ContentResolver crThumb = context.getContentResolver();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			bitmap = QLMethodsCompat.getThumbnail(crThumb, cursor.getInt(0),
					kind, options);
		}
		return bitmap;
	}

	public static String getLatestImage(Activity context) {
		String latestImage = null;
		String[] items = { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DATA };
		Cursor cursor = context.managedQuery(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, items, null,
				null, MediaStore.Images.Media._ID + " desc");

		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				latestImage = cursor.getString(1);
				break;
			}
		}

		return latestImage;
	}

	/**
	 *
	 * @param img_size
	 * @param square_size
	 * @return
	 */
	public static int[] scaleImageSize(int[] img_size, int square_size) {
		if (img_size[0] <= square_size && img_size[1] <= square_size)
			return img_size;
		double ratio = square_size
				/ (double) Math.max(img_size[0], img_size[1]);
		return new int[] { (int) (img_size[0] * ratio),
				(int) (img_size[1] * ratio) };
	}

	public static void createImageThumbnail(Context context,
											String largeImagePath, String thumbfilePath, int widthAndHightSize,
											int quality) throws IOException {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = 1;

		Bitmap cur_bitmap = getBitmapByPath(largeImagePath, opts);

		if (cur_bitmap == null)
			return;

		int[] cur_img_size = new int[] { cur_bitmap.getWidth(),
				cur_bitmap.getHeight() };

		int[] new_img_size = scaleImageSize(cur_img_size, widthAndHightSize);

		Bitmap thb_bitmap = zoomBitmap(cur_bitmap, new_img_size[0],
				new_img_size[1]);

		saveImageToSD(null, thumbfilePath, thb_bitmap, quality);
	}

	/** 保存方法 */
	public static boolean saveBitmap(Bitmap bitmap, String path) {
		boolean isSave = false;
		File f = new File(path);
//		if (f.exists()) {
//			f.delete();
//		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			isSave = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSave = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSave = false;
		}
		return isSave;
	}


	public static void saveImageToSD(Context ctx, String filePath,
									 Bitmap bitmap, int quality) throws IOException {
		if (bitmap != null) {
			File file = new File(filePath.substring(0,
					filePath.lastIndexOf(File.separator)));
			if (!file.exists()) {
				file.mkdirs();
			}
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(filePath));
			bitmap.compress(CompressFormat.JPEG, quality, bos);
			bos.flush();
			bos.close();
			if (ctx != null) {
				scanPhoto(ctx, filePath);
			}
		}
	}

	private static void scanPhoto(Context ctx, String imgFileName) {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File file = new File(imgFileName);
		Uri contentUri = Uri.fromFile(file);
		mediaScanIntent.setData(contentUri);
		ctx.sendBroadcast(mediaScanIntent);
	}

	public static Bitmap getBitmapByPath(String filePath,
										 BitmapFactory.Options opts) {
		FileInputStream fis = null;
		Bitmap bitmap = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bitmap = BitmapFactory.decodeStream(fis, null, opts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return bitmap;
	}

	/**
	 * ??�大缩�????��??
	 *
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		Bitmap newbmp = null;
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Matrix matrix = new Matrix();
			float scaleWidht = ((float) w / width);
			float scaleHeight = ((float) h / height);
			matrix.postScale(scaleWidht, scaleHeight);
			newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
					true);
		}
		return newbmp;
	}


	/**
	 * (缩�??)???�???��??
	 *
	 *            Activity
	 * @param bitmap
	 * @return
	 */

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}


	/**
	 * ??��??�????影�????��????��??
	 *
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}

	/**
	 * �?Drawable�????�?Bitmap
	 *
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;

	}

	/**
	 * �?bitmap�????�?drawable
	 *
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmapToDrawable(Bitmap bitmap) {
		Drawable drawable = new BitmapDrawable(null, bitmap);
		return drawable;
	}

	/**
	 * @param file
	 * @return
	 */
	public static String getImageType(File file) {
		if (file == null || !file.exists()) {
			return null;
		}
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			String type = getImageType(in);
			return type;
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * @param in
	 * @return
	 */
	public static String getImageType(InputStream in) {
		if (in == null) {
			return null;
		}
		try {
			byte[] bytes = new byte[8];
			in.read(bytes);
			return getImageType(bytes);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * @param bytes
	 *            2~8 byte at beginning of the image file
	 * @return image mimetype or null if the file is not image
	 */
	public static String getImageType(byte[] bytes) {
		if (isJPEG(bytes)) {
			return "image/jpeg";
		}
		if (isGIF(bytes)) {
			return "image/gif";
		}
		if (isPNG(bytes)) {
			return "image/png";
		}
		if (isBMP(bytes)) {
			return "application/x-bmp";
		}
		return null;
	}

	private static boolean isJPEG(byte[] b) {
		if (b.length < 2) {
			return false;
		}
		return (b[0] == (byte) 0xFF) && (b[1] == (byte) 0xD8);
	}

	private static boolean isGIF(byte[] b) {
		if (b.length < 6) {
			return false;
		}
		return b[0] == 'G' && b[1] == 'I' && b[2] == 'F' && b[3] == '8'
				&& (b[4] == '7' || b[4] == '9') && b[5] == 'a';
	}

	private static boolean isPNG(byte[] b) {
		if (b.length < 8) {
			return false;
		}
		return (b[0] == (byte) 137 && b[1] == (byte) 80 && b[2] == (byte) 78
				&& b[3] == (byte) 71 && b[4] == (byte) 13 && b[5] == (byte) 10
				&& b[6] == (byte) 26 && b[7] == (byte) 10);
	}

	private static boolean isBMP(byte[] b) {
		if (b.length < 2) {
			return false;
		}
		return (b[0] == 0x42) && (b[1] == 0x4d);
	}

	/**
	 * @param uri
	 * @return E-mail:mr.huangwenwei@gmail.com
	 */
	public static String getImagePath(Uri uri, Activity context) {

		String[] projection = { MediaColumns.DATA };
		Cursor cursor = context.getContentResolver().query(uri, projection,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			int columIndex = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			String ImagePath = cursor.getString(columIndex);
			cursor.close();
			return ImagePath;
		}

		return uri.toString();
	}

	static Bitmap bitmap = null;

	/**
	 * @param uri
	 * @param context
	 *            E-mail:mr.huangwenwei@gmail.com
	 */
	public static Bitmap loadPicasaImageFromGalley(final Uri uri,
												   final Activity context) {

		String[] projection = { MediaColumns.DATA, MediaColumns.DISPLAY_NAME };
		Cursor cursor = context.getContentResolver().query(uri, projection,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();

			int columIndex = cursor.getColumnIndex(MediaColumns.DISPLAY_NAME);
			if (columIndex != -1) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							bitmap = android.provider.MediaStore.Images.Media
									.getBitmap(context.getContentResolver(),
											uri);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}).start();
			}
			cursor.close();
			return bitmap;
		} else
			return null;
	}

	public static Bitmap saveImageCapture(View v, String path) {
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache();
		Bitmap bp = v.getDrawingCache();
		if (bp != null) {
			File file = new File(path);
			try {
				FileOutputStream fo = new FileOutputStream(file);
				bp.compress(Bitmap.CompressFormat.JPEG, 100, fo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bp;
	}

	public static Bitmap loadImgThumbnail(String filePath, int w, int h) {
		Bitmap bitmap = getBitmapByPath(filePath);
		return zoomBitmap(bitmap, w, h);
	}

	public static Bitmap getBitmapByPath(String filePath) {
		return getBitmapByPath(filePath, null);
	}



	public static Bitmap getTempBitmap(String path,int inSampleSize){
		BitmapFactory.Options bfOptions=new BitmapFactory.Options();
		bfOptions.inDither=false;
		bfOptions.inPurgeable=true;
		bfOptions.inTempStorage=new byte[12*1024 * 1024];
		bfOptions.inSampleSize=inSampleSize;
//        bfOptions.inJustDecodeBounds = true;
		File file = new File(path);
		FileInputStream fs=null;
		Bitmap bmp = null;
		try {
			fs = new FileInputStream(file);
			if(fs != null)
				bmp = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
			AppLog.D("bmp:" +bmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bmp;
	}
}
