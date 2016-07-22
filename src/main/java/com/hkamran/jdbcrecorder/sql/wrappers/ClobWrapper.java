package com.hkamran.jdbcrecorder.sql.wrappers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;

public class ClobWrapper implements Clob {

	SerialClob clob;

	public ClobWrapper(Clob clob) {
		try {
			this.clob = new SerialClob(clob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ClobWrapper(char[] chars) {
		try {
			this.clob = new SerialClob(chars);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long length() throws SQLException {
		return clob.length();
		
	}

	@Override
	public String getSubString(long pos, int length) throws SQLException {
		return clob.getSubString(pos, length);
	}

	@Override
	public Reader getCharacterStream() throws SQLException {
		return clob.getCharacterStream();
	}

	@Override
	public InputStream getAsciiStream() throws SQLException {
		return clob.getAsciiStream();
	}

	@Override
	public long position(String searchstr, long start) throws SQLException {
		return clob.position(searchstr, start);
	}

	@Override
	public long position(Clob searchstr, long start) throws SQLException {
		return clob.position(searchstr, start);
	}

	@Override
	public int setString(long pos, String str) throws SQLException {
		return clob.setString(pos, str);
	}

	@Override
	public int setString(long pos, String str, int offset, int length)
			throws SQLException {
		return clob.setString(pos, str, offset, length);
	}

	@Override
	public OutputStream setAsciiStream(long pos) throws SQLException {
		return clob.setAsciiStream(pos);
	}

	@Override
	public Writer setCharacterStream(long pos) throws SQLException {
		return clob.setCharacterStream(pos);
	}

	@Override
	public void truncate(long length) throws SQLException {
		clob.truncate(length);
	}

	@Override
	public void free() throws SQLException {

	}

	@Override
	public Reader getCharacterStream(long pos, long length) throws SQLException {
		return clob.getCharacterStream(pos, length);
	}

	public String toString() {
		StringBuffer strOut = new StringBuffer();
		String aux;
		try {
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			while ((aux = br.readLine()) != null) {
				strOut.append(aux);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String clobStr = strOut.toString();
		return clobStr;
	}

}
