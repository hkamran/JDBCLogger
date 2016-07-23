package com.hkamran.jdbclogger.sql.wrappers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * This class represents a SQL that is going to be executed by a JDBC Statement.
 * 
 * @author Hooman Kamran
 */
public class QueryWrapper {
	public String vars[];
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	public String query;

	public QueryWrapper(String query) {
		int countVars = StringUtils.countMatches(query, "?");
		vars = new String[countVars];
		this.query = query.replaceAll("\\?", "%s");
		this.query = this.query.toUpperCase();
	}

	public void clearParameters() {
		int countVars = StringUtils.countMatches(query, "?");
		vars = new String[countVars];
		
	}

	public void setDouble(int i, Double arg0) {
		vars[i - 1] = "'" + arg0 + "'";
	}

	public void setString(int i, String arg0) {
		vars[i - 1] = "'" + arg0 + "'";
	}

	public void setObject(int i, String arg0) {
		vars[i - 1] = arg0;
	}

	public void setNull(int i, int type) {
		vars[i - 1] = "null (" + type + ")";
	}

	public String toString() {
		try {
			return String.format(query, vars);
		} catch (Exception e) {
			e.printStackTrace();
			return query + " " + vars.length;
		}
	}

	public void setBoolean(int i, boolean x) {
		vars[i - 1] = new Boolean(x).toString();

	}

	public void setByte(int i, byte x) {
		vars[i - 1] = new Byte(x).toString();
	}

	public void setShort(int i, short x) { 
		vars[i - 1] = new Short(x).toString();

	}

	public void setInt(int i, int x) {
		vars[i - 1] = new Integer(x).toString();

	}

	public void setLong(int i, long x) {
		vars[i - 1] = new Long(x).toString();

	}

	public void setFloat(int i, float x) {
		vars[i - 1] = new Float(x).toString();

	}

	public void setBigDecimal(int i, BigDecimal x) {
		vars[i - 1] = x.toString();

	}

	public void setBytes(int i, byte[] x) {
		try {
			vars[i - 1] = toHex(new String(x, "US-ASCII"));
		} catch (UnsupportedEncodingException e) {
			vars[i - 1] = "byte array";
		}

	}

	private String toHex(String arg) {
		return String.format("%x", new BigInteger(1, arg.getBytes(/* YOUR_CHARSET? */)));
	}

	public void setDate(int i, Date x) {
		vars[i - 1] = formatter.format(x);
	}

	public void setTime(int i, Time x) {
		vars[i - 1] = formatter.format(x);

	}

	public void setAsciiStream(int i, InputStream x, int length) {
		try {
			vars[i - 1] = IOUtils.toString(x);
		} catch (IOException e) {
			vars[i - 1] = "";
		}

	}

	public void setTimestamp(int i, Timestamp x) {
		vars[i - 1] = formatter.format(x);

	}

	public void setUnicodeStream(int i, InputStream x, int length) {
		vars[i - 1] = "[input stream]";

	}

	public void setBinaryStream(int i, InputStream x, int length) {
		vars[i - 1] = "[input stream]";

	}

	public void setObject(int i, Object x, int targetSqlType) {
		vars[i - 1] = x.toString();
	}

	public void setObject(int i, Object x) {
		vars[i - 1] = x.toString();

	}

	public void setCharacterStream(int i, Reader reader, int length) {
		vars[i - 1] = "[reader]";

	}

	public void setRef(int i, Ref x) {
		vars[i - 1] = "[ref]";

	}

	public void setBlob(int i, Blob x) {
		InputStream is;
		try {
			is = x.getBinaryStream();
			vars[i - 1] = IOUtils.toString(is);
			return;
		} catch (SQLException e) {
			
		} catch (IOException e) {

		}
		vars[i - 1] = "";

	}


	public void setClob(int i, Clob x) {
		vars[i - 1] = "[clob]";
	}

	public void setArray(int i, Array x) {
		vars[i - 1] = "[array]";

	}

	public void setDate(int i, Date x, Calendar cal) {
		// TODO Auto-generated method stub

	}

	public void setTime(int i, Time x, Calendar cal) {
		// TODO Auto-generated method stub

	}

	public void setTimestamp(int i, Timestamp x, Calendar cal) {
		// TODO Auto-generated method stub

	}

	public void setNull(int i, int sqlType, String typeName) {
		vars[i - 1] = "null";

	}

	public void setURL(int i, URL x) {
		vars[i - 1] = x.toString();

	}

	public void setRowId(int i, RowId x) {
		vars[i - 1] = x.toString();

	}

	public void setNString(int i, String value) {
		vars[i - 1] = value;

	}

	public void setNCharacterStream(int i, Reader value, long length) {
		vars[i - 1] = "[character stream]";

	}

	public void setNClob(int i, NClob value) {
		vars[i - 1] = "[nclob]";

	}

	public void setClob(int i, Reader reader, long length) {
		vars[i - 1] = "[clob]";
	}

	public void setBlob(int i, InputStream inputStream, long length) {
		vars[i - 1] = "[blob]";
	}

	public void setNClob(int i, Reader reader, long length) {
		vars[i - 1] = "[nclob]";

	}

	public void setSQLXML(int i, SQLXML xmlObject) {
		vars[i - 1] = xmlObject.toString();

	}
}
