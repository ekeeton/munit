/**
 * Mule Development Kit
 * Copyright 2010-2011 (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.munit;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Module to test database connections</p>
 *
 * @author Federico, Fernando
 */
@Module(name="dbserver", schemaVersion="1.0")
public class DBServerModule
{
    /**
     * <p>JBDC url</p>
     */
    @Configurable
    private String jdbcUrl;

    /**
     * <p>Script to create the database.</p>
     */
    @Configurable
    private String creationalScript;
    private Connection connection;


    /**
     * <p>Start the server.</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:start}
     *
     */
    @Processor
    public void startDbServer()
    {
        try {

            addJdbcToClassLoader();
            connection = DriverManager.getConnection(jdbcUrl);
            Statement stmt = connection.createStatement();
            String[] expressions = creationalScript.split(";");
            for ( String expression : expressions)
            {
                stmt.execute(expression);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not start the database server", e);
        }
    }

    /**
     * <p>Executes a SQL query</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:execute}
     *
     * @param sql query to be executed
     * @return result of the SQL query.
     */
    @Processor
    public Object execute(String sql)
    {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.execute(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * <p>Stops the server.</p>
     *
     * {@sample.xml ../../../doc/DBServer-connector.xml.sample dbserver:stop}
     */
    @Processor
    public void stopDbServer() {
        try {
            if ( connection != null ) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not stop the database server", e);
        }
    }

    private void addJdbcToClassLoader() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setCreationalScript(String creationalScript) {
        this.creationalScript = creationalScript;
    }
}
