package com.hkamran.jdbclogger.sql.wrappers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

public class BlobWrapper implements Blob {

	SerialBlob blob;

	public BlobWrapper(Blob blob) {
		try {
			this.blob = new SerialBlob(blob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public BlobWrapper(byte[] bytes) {
		try {
			this.blob = new SerialBlob(bytes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public long length() throws SQLException {
		return blob.length();
	}

	@Override
	public byte[] getBytes(long pos, int length) throws SQLException {
		return blob.getBytes(pos, length);
	}

	@Override
	public InputStream getBinaryStream() throws SQLException {
		return blob.getBinaryStream();
	}

	@Override
	public long position(byte[] pattern, long start) throws SQLException {
		return blob.position(pattern, start);
	}

	@Override
	public long position(Blob pattern, long start) throws SQLException {
		return blob.position(pattern, start);
	}

	@Override
	public int setBytes(long pos, byte[] bytes) throws SQLException {
		return blob.setBytes(pos, bytes);
	}

	@Override
	public int setBytes(long pos, byte[] bytes, int offset, int length)
			throws SQLException {
		return blob.setBytes(pos, bytes, offset, length);
	}

	@Override
	public OutputStream setBinaryStream(long pos) throws SQLException {
		return blob.setBinaryStream(pos);
	}

	@Override
	public void truncate(long len) throws SQLException {
		blob.truncate(len);
	}

	@Override
	public void free() throws SQLException {

	}

	@Override
	public InputStream getBinaryStream(long pos, long length)
			throws SQLException {
		return blob.getBinaryStream(pos, length);
	}
	
	public byte[] toByteArray() {
		InputStream is = null;
		byte[] content = null; 
		try {
			is = blob.getBinaryStream();
			content = IOUtils.toByteArray(is);

		} catch (SerialException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try { if (is != null) is.close(); } catch (Exception e) {};
		}
		return content;
	}
	
	public String toString() {
		return new JSONArray(toByteArray()).toString();
	}

}
