package cj.software.experiments.camunda._06_subprocesses.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._06_subprocesses.entity.CheckedResource;

@Component
public class NumEntriesReader
{
	private Logger logger = LoggerFactory.getLogger(NumEntriesReader.class);

	public long readNumEntries(String pCorrelationId, CheckedResource pCheckedResource)
			throws SQLException
	{
		String lConnectionString = pCheckedResource.getDatabaseConnection();
		String lUser = pCheckedResource.getUser();
		String lPassword = pCheckedResource.getPassword();

		this.logger.info("{}: connect to  {} user {}", pCorrelationId, lConnectionString, lUser);

		try (Connection lConnection = DriverManager.getConnection(
				lConnectionString,
				lUser,
				lPassword))
		{
			this.logger.info("{}: connected", pCorrelationId);
			try (Statement lStmt = lConnection.createStatement())
			{
				String lSql = pCheckedResource.getSelectStatement();
				this.logger.info("{}: {}", pCorrelationId, lSql);
				try (ResultSet lRS = lStmt.executeQuery(lSql))
				{
					lRS.next();
					long lResult = lRS.getLong(1);
					return lResult;
				}
			}
		}
	}
}
